package br.com.globallabs.api.rest;

import br.com.globallabs.model.Jedi;
import br.com.globallabs.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/jedis")
public class JediResource {

    @Autowired
    private JediRepository jediRepository;

    @GetMapping
    public List<Jedi> listAll() {
        return jediRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jedi> searchId(@PathVariable("id") final Long id) {
        Optional<Jedi> jedi = jediRepository.findById(id);

        if (jedi.isPresent()) {
            return ResponseEntity.ok(jedi.get());
        }

        return ResponseEntity.notFound().build();
    }

}
