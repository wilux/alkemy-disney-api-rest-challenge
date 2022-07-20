package alkemy.challenge.disney_api_rest.controller;

import alkemy.challenge.disney_api_rest.domain.Gender;
import alkemy.challenge.disney_api_rest.model.MovieDTO;
import alkemy.challenge.disney_api_rest.repos.GenderRepository;
import alkemy.challenge.disney_api_rest.service.MovieService;
import alkemy.challenge.disney_api_rest.util.WebUtils;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;
    private final GenderRepository genderRepository;

    public MovieController(final MovieService movieService,
            final GenderRepository genderRepository) {
        this.movieService = movieService;
        this.genderRepository = genderRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("genderValues", genderRepository.findAll().stream().collect(
                Collectors.toMap(Gender::getId, Gender::getName)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("movies", movieService.findAll());
        return "movie/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("movie") final MovieDTO movieDTO) {
        return "movie/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("movie") @Valid final MovieDTO movieDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "movie/add";
        }
        movieService.create(movieDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("movie.create.success"));
        return "redirect:/movies";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("movie", movieService.get(id));
        return "movie/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("movie") @Valid final MovieDTO movieDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "movie/edit";
        }
        movieService.update(id, movieDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("movie.update.success"));
        return "redirect:/movies";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        final String referencedWarning = movieService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            movieService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("movie.delete.success"));
        }
        return "redirect:/movies";
    }

}
