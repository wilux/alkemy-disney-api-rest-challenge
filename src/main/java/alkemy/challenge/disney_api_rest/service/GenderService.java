package alkemy.challenge.disney_api_rest.service;

import alkemy.challenge.disney_api_rest.domain.Gender;
import alkemy.challenge.disney_api_rest.model.GenderDTO;
import alkemy.challenge.disney_api_rest.repos.GenderRepository;
import alkemy.challenge.disney_api_rest.util.WebUtils;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class GenderService {

    private final GenderRepository genderRepository;

    public GenderService(final GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    public List<GenderDTO> findAll() {
        return genderRepository.findAll(Sort.by("id"))
                .stream()
                .map(gender -> mapToDTO(gender, new GenderDTO()))
                .collect(Collectors.toList());
    }

    public GenderDTO get(final Long id) {
        return genderRepository.findById(id)
                .map(gender -> mapToDTO(gender, new GenderDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final GenderDTO genderDTO) {
        final Gender gender = new Gender();
        mapToEntity(genderDTO, gender);
        return genderRepository.save(gender).getId();
    }

    public void update(final Long id, final GenderDTO genderDTO) {
        final Gender gender = genderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(genderDTO, gender);
        genderRepository.save(gender);
    }

    public void delete(final Long id) {
        genderRepository.deleteById(id);
    }

    private GenderDTO mapToDTO(final Gender gender, final GenderDTO genderDTO) {
        genderDTO.setId(gender.getId());
        genderDTO.setName(gender.getName());
        return genderDTO;
    }

    private Gender mapToEntity(final GenderDTO genderDTO, final Gender gender) {
        gender.setName(genderDTO.getName());
        return gender;
    }

    @Transactional
    public String getReferencedWarning(final Long id) {
        final Gender gender = genderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!gender.getGenderMovies().isEmpty()) {
            return WebUtils.getMessage("gender.movie.manyToOne.referenced", gender.getGenderMovies().iterator().next().getId());
        }
        return null;
    }

}
