package alkemy.challenge.disney_api_rest.repos;

import alkemy.challenge.disney_api_rest.domain.Character;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    // Mis modificaciones
    List<Character> findByName(String name);

    List<Character> findByAge(String age);

    List<Character> findByMovie(Long movie);

    @Transactional
    List<Character> deleteByIdAndMovieId(Long idCharacter, long idMovie);

}
