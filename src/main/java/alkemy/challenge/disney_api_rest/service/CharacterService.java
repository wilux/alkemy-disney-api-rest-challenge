package alkemy.challenge.disney_api_rest.service;

import alkemy.challenge.disney_api_rest.domain.Character;
import alkemy.challenge.disney_api_rest.domain.Movie;
import alkemy.challenge.disney_api_rest.model.CharacterDTO;
import alkemy.challenge.disney_api_rest.repos.CharacterRepository;
import alkemy.challenge.disney_api_rest.repos.MovieRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final MovieRepository movieRepository;

    public CharacterService(final CharacterRepository characterRepository,
            final MovieRepository movieRepository) {
        this.characterRepository = characterRepository;
        this.movieRepository = movieRepository;
    }

    public List<CharacterDTO> findAll() {
        return characterRepository.findAll(Sort.by("id"))
                .stream()
                .map(character -> mapToDTO(character, new CharacterDTO()))
                .collect(Collectors.toList());
    }

    public CharacterDTO get(final Long id) {
        return characterRepository.findById(id)
                .map(character -> mapToDTO(character, new CharacterDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final CharacterDTO characterDTO) {
        final Character character = new Character();
        mapToEntity(characterDTO, character);
        return characterRepository.save(character).getId();
    }

    public void update(final Long id, final CharacterDTO characterDTO) {
        final Character character = characterRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(characterDTO, character);
        characterRepository.save(character);
    }

    public void delete(final Long id) {
        characterRepository.deleteById(id);
    }

    private CharacterDTO mapToDTO(final Character character, final CharacterDTO characterDTO) {
        characterDTO.setId(character.getId());
        characterDTO.setImage(character.getImage());
        characterDTO.setName(character.getName());
        characterDTO.setAge(character.getAge());
        characterDTO.setWeight(character.getWeight());
        characterDTO.setMovie(character.getMovie() == null ? null : character.getMovie().getId());
        return characterDTO;
    }

    private Character mapToEntity(final CharacterDTO characterDTO, final Character character) {
        character.setImage(characterDTO.getImage());
        character.setName(characterDTO.getName());
        character.setAge(characterDTO.getAge());
        character.setWeight(characterDTO.getWeight());
        final Movie movie = characterDTO.getMovie() == null ? null : movieRepository.findById(characterDTO.getMovie())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "movie not found"));
        character.setMovie(movie);
        return character;
    }

}
