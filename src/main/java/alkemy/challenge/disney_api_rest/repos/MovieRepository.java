package alkemy.challenge.disney_api_rest.repos;

import alkemy.challenge.disney_api_rest.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MovieRepository extends JpaRepository<Movie, Long> {
}
