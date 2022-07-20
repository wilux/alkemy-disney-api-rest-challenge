package alkemy.challenge.disney_api_rest.repos;

import alkemy.challenge.disney_api_rest.domain.Gender;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GenderRepository extends JpaRepository<Gender, Long> {
}
