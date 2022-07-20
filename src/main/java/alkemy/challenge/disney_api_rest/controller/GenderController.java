package alkemy.challenge.disney_api_rest.controller;

import alkemy.challenge.disney_api_rest.model.GenderDTO;
import alkemy.challenge.disney_api_rest.service.GenderService;
import alkemy.challenge.disney_api_rest.util.WebUtils;
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
@RequestMapping("/genders")
public class GenderController {

    private final GenderService genderService;

    public GenderController(final GenderService genderService) {
        this.genderService = genderService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("genders", genderService.findAll());
        return "gender/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("gender") final GenderDTO genderDTO) {
        return "gender/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("gender") @Valid final GenderDTO genderDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "gender/add";
        }
        genderService.create(genderDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("gender.create.success"));
        return "redirect:/genders";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("gender", genderService.get(id));
        return "gender/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("gender") @Valid final GenderDTO genderDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "gender/edit";
        }
        genderService.update(id, genderDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("gender.update.success"));
        return "redirect:/genders";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        final String referencedWarning = genderService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            genderService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("gender.delete.success"));
        }
        return "redirect:/genders";
    }

}
