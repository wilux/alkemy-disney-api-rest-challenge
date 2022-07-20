package alkemy.challenge.disney_api_rest.rest;

import alkemy.challenge.disney_api_rest.model.CharacterDTO;
import alkemy.challenge.disney_api_rest.service.CharacterService;
import alkemy.challenge.disney_api_rest.view.View;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value = "/api/characters", produces = MediaType.APPLICATION_JSON_VALUE)
public class CharacterResource {

    private final CharacterService characterService;

    public CharacterResource(final CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping
    public ResponseEntity<List<CharacterDTO>> getAllCharacters() {
        return ResponseEntity.ok(characterService.findAll());
    }

    // Mis modificaciones

    @GetMapping(params = { "name" })
    @JsonView(value = View.UserView.External.class)
    public ResponseEntity<List<CharacterDTO>> getCharacterByName(@RequestParam String name) {

        return ResponseEntity.ok(characterService.getByName(name));

    }

    @GetMapping(params = { "age" })
    @JsonView(value = View.UserView.External.class)
    public ResponseEntity<List<CharacterDTO>> getCharacterByAge(@RequestParam String age) {

        return ResponseEntity.ok(characterService.getByAge(age));

    }

    @GetMapping(params = { "movies" })
    @JsonView(value = View.UserView.External.class)
    public ResponseEntity<List<CharacterDTO>> getCharacterByMovie(@RequestParam Long movies) {

        return ResponseEntity.ok(characterService.getByMovie(movies));

    }

    // Mis modificaciones

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> getCharacter(@PathVariable final Long id) {
        return ResponseEntity.ok(characterService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createCharacter(
            @RequestBody @Valid final CharacterDTO characterDTO) {
        return new ResponseEntity<>(characterService.create(characterDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCharacter(@PathVariable final Long id,
            @RequestBody @Valid final CharacterDTO characterDTO) {
        characterService.update(id, characterDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteCharacter(@PathVariable final Long id) {
        characterService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
