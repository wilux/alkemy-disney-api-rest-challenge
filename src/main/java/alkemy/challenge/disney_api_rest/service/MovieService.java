package alkemy.challenge.disney_api_rest.service;

import alkemy.challenge.disney_api_rest.domain.Gender;
import alkemy.challenge.disney_api_rest.domain.Movie;
import alkemy.challenge.disney_api_rest.model.MovieDTO;
import alkemy.challenge.disney_api_rest.repos.GenderRepository;
import alkemy.challenge.disney_api_rest.repos.MovieRepository;
import alkemy.challenge.disney_api_rest.util.WebUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final GenderRepository genderRepository;

    public MovieService(final MovieRepository movieRepository,
            final GenderRepository genderRepository) {
        this.movieRepository = movieRepository;
        this.genderRepository = genderRepository;
    }

    public List<MovieDTO> findAll() {
        return movieRepository.findAll(Sort.by("id"))
                .stream()
                .map(movie -> mapToDTO(movie, new MovieDTO()))
                .collect(Collectors.toList());
    }

    public List<MovieDTO> findAllByOrder(String order) {

        if (order.equals("DESC")) {
            return movieRepository.findAll(Sort.by("id"))
                    .stream()
                    .map(movie -> mapToDTO(movie, new MovieDTO()))
                    .sorted(Comparator.comparing(MovieDTO::getDateCreated).reversed())
                    .collect(Collectors.toList());
        } else {
            return movieRepository.findAll(Sort.by("id"))
                    .stream()
                    .map(movie -> mapToDTO(movie, new MovieDTO()))
                    .collect(Collectors.toList());
        }

    }

    public MovieDTO get(final Long id) {
        return movieRepository.findById(id)
                .map(movie -> mapToDTO(movie, new MovieDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final MovieDTO movieDTO) {
        final Movie movie = new Movie();
        mapToEntity(movieDTO, movie);
        return movieRepository.save(movie).getId();
    }

    public void update(final Long id, final MovieDTO movieDTO) {
        final Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(movieDTO, movie);
        movieRepository.save(movie);
    }

    public void delete(final Long id) {
        movieRepository.deleteById(id);
    }

    private MovieDTO mapToDTO(final Movie movie, final MovieDTO movieDTO) {
        movieDTO.setId(movie.getId());
        movieDTO.setImage(movie.getImage());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setDateCreated(movie.getDateCreated());
        movieDTO.setGender(movie.getGender() == null ? null : movie.getGender().getId());
        return movieDTO;
    }

    private Movie mapToEntity(final MovieDTO movieDTO, final Movie movie) {
        movie.setImage(movieDTO.getImage());
        movie.setTitle(movieDTO.getTitle());
        final Gender gender = movieDTO.getGender() == null ? null
                : genderRepository.findById(movieDTO.getGender())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "gender not found"));
        movie.setGender(gender);
        return movie;
    }

    @Transactional
    public String getReferencedWarning(final Long id) {
        final Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!movie.getMovieCharacters().isEmpty()) {
            return WebUtils.getMessage("movie.character.manyToOne.referenced",
                    movie.getMovieCharacters().iterator().next().getId());
        }
        return null;
    }

}
