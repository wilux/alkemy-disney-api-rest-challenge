package alkemy.challenge.disney_api_rest.rest;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import alkemy.challenge.disney_api_rest.domain.User;
import alkemy.challenge.disney_api_rest.model.LoginCredentials;
import alkemy.challenge.disney_api_rest.repos.UserRepository;
import alkemy.challenge.disney_api_rest.security.JWTUtil;
import alkemy.challenge.disney_api_rest.util.Mail;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.Collections;
import java.util.Map;

@RestController // Marks the class a rest controller
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthResource {

    // Injecting Dependencies
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Mail mail;

    // Defining the function to handle the POST route for registering a user
    @PostMapping("/register")
    @ApiResponse(responseCode = "201")
    public Map<String, Object> registerHandler(@RequestBody User user) {
        // Encoding Password using Bcrypt
        String encodedPass = passwordEncoder.encode(user.getPassword());

        // Setting the encoded password
        user.setPassword(encodedPass);

        // Persisting the User Entity to H2 Database
        user = userRepo.save(user);

        // Generating JWT
        String token = jwtUtil.generateToken(user.getEmail());

        // Send Mail
        mail.sendMailTo(user.getEmail());

        // Responding with JWT
        return Collections.singletonMap("jwt-token", token);

    }

    // Defining the function to handle the POST route for logging in a user
    @PostMapping("/login")
    @ApiResponse(responseCode = "201")
    public Map<String, Object> loginHandler(@RequestBody LoginCredentials body) {
        try {
            // Creating the Authentication Token which will contain the credentials for
            // authenticating
            // This token is used as input to the authentication process
            UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
                    body.getEmail(), body.getPassword());

            // Authenticating the Login Credentials
            authManager.authenticate(authInputToken);

            // If this point is reached it means Authentication was successful
            // Generate the JWT
            String token = jwtUtil.generateToken(body.getEmail());

            // Respond with the JWT
            return Collections.singletonMap("jwt-token", token);
        } catch (AuthenticationException authExc) {
            // Auhentication Failed
            throw new RuntimeException("Invalid Login Credentials");

        }
    }

}
