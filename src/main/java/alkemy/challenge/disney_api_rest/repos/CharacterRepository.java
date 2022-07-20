package alkemy.challenge.disney_api_rest.repos;

import alkemy.challenge.disney_api_rest.domain.Character;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CharacterRepository extends JpaRepository<Character, Long> {
}
