package alkemy.challenge.disney_api_rest.repos;

import alkemy.challenge.disney_api_rest.domain.Character;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    // Mis modificaciones
    List<Character> findByName(String name);

    List<Character> findByAge(String age);

    List<Character> findByMovie(Long id);

    // List<Character> findByIdAndCharacterMovie_Id(Long id, Long id1);
    // Mis modificaciones
}
