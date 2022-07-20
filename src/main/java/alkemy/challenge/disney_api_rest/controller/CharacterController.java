package alkemy.challenge.disney_api_rest.controller;

import alkemy.challenge.disney_api_rest.domain.Movie;
import alkemy.challenge.disney_api_rest.model.CharacterDTO;
import alkemy.challenge.disney_api_rest.repos.MovieRepository;
import alkemy.challenge.disney_api_rest.service.CharacterService;
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
@RequestMapping("/characters")
public class CharacterController {

    private final CharacterService characterService;
    private final MovieRepository movieRepository;

    public CharacterController(final CharacterService characterService,
            final MovieRepository movieRepository) {
        this.characterService = characterService;
        this.movieRepository = movieRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("movieValues", movieRepository.findAll().stream().collect(
                Collectors.toMap(Movie::getId, Movie::getImage)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("characters", characterService.findAll());
        return "character/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("character") final CharacterDTO characterDTO) {
        return "character/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("character") @Valid final CharacterDTO characterDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "character/add";
        }
        characterService.create(characterDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("character.create.success"));
        return "redirect:/characters";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("character", characterService.get(id));
        return "character/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("character") @Valid final CharacterDTO characterDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "character/edit";
        }
        characterService.update(id, characterDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("character.update.success"));
        return "redirect:/characters";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        characterService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("character.delete.success"));
        return "redirect:/characters";
    }

}
