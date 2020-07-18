package br.com.globallabs.controller;

import br.com.globallabs.model.Jedi;
import br.com.globallabs.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class JediController {

    @Autowired
    private JediRepository jediRepository;

    @GetMapping("/jedi")
    public ModelAndView jedi() {
        ModelAndView mv = new ModelAndView("jedi");
        mv.addObject("allJedi", jediRepository.findAll());

        return mv;
    }

    @GetMapping("/search")
    public ModelAndView jediSearch(@RequestParam(value = "name") final String name) {
        ModelAndView mv = new ModelAndView("jedi");
        mv.addObject("allJedi", jediRepository.findByNameContainingIgnoreCase(name));

        return mv;
    }

    @GetMapping("/new-jedi")
    public String createJedi(Model model) {
        model.addAttribute("jedi", new Jedi());

        return "new-jedi";
    }

    @PostMapping("/jedi")
    public String createJedi(@Valid @ModelAttribute Jedi jedi, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            if (jedi.getId() == null) {
                return "new-jedi";
            } else {
                return "edit-jedi";
            }
        }

        jediRepository.save(jedi);
        redirect.addFlashAttribute("message", "Jedi successfully saved.");

        return "redirect:jedi";
    }

    @GetMapping("/jedi/{id}/delete")
    public String deleteJedi(@PathVariable("id") final Long id, RedirectAttributes redirect) {
        Optional<Jedi> jedi = jediRepository.findById(id);

        if (jedi.isPresent()) {
            jediRepository.delete(jedi.get());
        }

        redirect.addFlashAttribute("message", "Jedi successfully deleted.");

        return "redirect:/jedi";
    }

    @GetMapping("/jedi/{id}/update")
    public String updateJedi(@PathVariable("id") final Long id, Model model) {
        Optional<Jedi> jedi = jediRepository.findById(id);

        model.addAttribute("jedi", jedi.get());

        return "edit-jedi";
    }

}
