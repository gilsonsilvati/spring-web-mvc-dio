package br.com.globallabs.controller;

import br.com.globallabs.model.Jedi;
import br.com.globallabs.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class JediController {

    @Autowired
    private JediRepository jediRepository;

    @GetMapping("/jedi")
    public ModelAndView jedi() {
        ModelAndView mv = new ModelAndView("jedi");
        mv.addObject("allJedi", jediRepository.getAllJedi());

        return mv;
    }

    @GetMapping("/new-jedi")
    public ModelAndView newJedi() {
        ModelAndView mv = new ModelAndView("new-jedi");
        mv.addObject("jedi", new Jedi());

        return mv;
    }

    @PostMapping("/jedi")
    public String createJedi(@Valid @ModelAttribute Jedi jedi, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "new-jedi";
        }

        jediRepository.add(jedi);

        attributes.addFlashAttribute("message", "Jedi cadastrado com sucesso!");

        return "redirect:jedi";
    }

}
