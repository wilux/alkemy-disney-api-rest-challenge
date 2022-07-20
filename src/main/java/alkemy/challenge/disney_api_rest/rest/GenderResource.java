package alkemy.challenge.disney_api_rest.rest;

import alkemy.challenge.disney_api_rest.model.GenderDTO;
import alkemy.challenge.disney_api_rest.service.GenderService;
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
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/genders", produces = MediaType.APPLICATION_JSON_VALUE)
public class GenderResource {

    private final GenderService genderService;

    public GenderResource(final GenderService genderService) {
        this.genderService = genderService;
    }

    @GetMapping
    public ResponseEntity<List<GenderDTO>> getAllGenders() {
        return ResponseEntity.ok(genderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenderDTO> getGender(@PathVariable final Long id) {
        return ResponseEntity.ok(genderService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createGender(@RequestBody @Valid final GenderDTO genderDTO) {
        return new ResponseEntity<>(genderService.create(genderDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGender(@PathVariable final Long id,
            @RequestBody @Valid final GenderDTO genderDTO) {
        genderService.update(id, genderDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteGender(@PathVariable final Long id) {
        genderService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
