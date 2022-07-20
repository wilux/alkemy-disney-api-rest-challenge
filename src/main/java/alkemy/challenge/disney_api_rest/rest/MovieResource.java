package alkemy.challenge.disney_api_rest.rest;

import alkemy.challenge.disney_api_rest.domain.Movie;
import alkemy.challenge.disney_api_rest.model.CharacterDTO;
import alkemy.challenge.disney_api_rest.model.MovieDTO;
import alkemy.challenge.disney_api_rest.service.CharacterService;
import alkemy.challenge.disney_api_rest.service.MovieService;
import alkemy.challenge.disney_api_rest.view.View;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/api/movies", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieResource {

    private final MovieService movieService;
    @Autowired
    private CharacterService characterService;

    public MovieResource(final MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovie(@PathVariable final Long id) {
        return ResponseEntity.ok(movieService.get(id));
    }

    // Mi codigo
    @JsonView(value = View.UserView.External.class)
    @GetMapping(params = { "order" })
    public ResponseEntity<List<MovieDTO>> getCharacterByOrder(@RequestParam String order) {
        order = order.toLowerCase();
        return ResponseEntity.ok(movieService.findAllByOrder(order));

    }

    @JsonView(value = View.UserView.External.class)
    @GetMapping(params = { "name" })
    public ResponseEntity<List<MovieDTO>> getCharacterByName(@RequestParam String name) {
        return ResponseEntity.ok(movieService.getByName(name));

    }

    @JsonView(value = View.UserView.External.class)
    @GetMapping(params = { "genre" })
    public ResponseEntity<MovieDTO> getCharacterByGenre(@RequestParam final Long genre) {
        return ResponseEntity.ok(movieService.get(genre));

    }

    // 9. Agregar/Remover personajes a una película
    // Deberá existir un endpoint que nos permita agregar/remover personajes a una
    // película.
    // Los endpoint deberán ser:
    // ● POST /movies/{idMovie}/characters/{idCharacter}
    // ● DELETE /movies/{idMovie}/characters/{idCharacter}

    @PostMapping("/{idMovie}/characters/{idCharacter}")
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createCharacter(
            @RequestBody @Valid final CharacterDTO characterDTO) {
        return new ResponseEntity<>(characterService.create(characterDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idMovie}/characters/{idCharacter}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteMovieCharacter(@PathVariable final Long idMovie,
            @PathVariable final Long idCharacter) {
        characterService.deleteByIdAndMovieId(idCharacter, idMovie);
        return ResponseEntity.noContent().build();
    }

    // Mi codigo

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createMovie(@RequestBody @Valid final MovieDTO movieDTO) {
        return new ResponseEntity<>(movieService.create(movieDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMovie(@PathVariable final Long id,
            @RequestBody @Valid final MovieDTO movieDTO) {
        movieService.update(id, movieDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteMovie(@PathVariable final Long id) {
        movieService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
