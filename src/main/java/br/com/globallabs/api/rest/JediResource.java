package br.com.globallabs.api.rest;

import br.com.globallabs.model.Jedi;
import br.com.globallabs.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Jedi create(@Valid @RequestBody Jedi jedi) {
        return jediRepository.save(jedi);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jedi> update(@PathVariable("id") final Long id, @Valid @RequestBody Jedi dto) {
        Optional<Jedi> jediEntity = jediRepository.findById(id);

        if (jediEntity.isPresent()) {
            var jedi = jediEntity.get();
            jedi.setName(dto.getName());
            jedi.setLastName(dto.getLastName());

            jedi = jediRepository.save(jedi);

            return ResponseEntity.ok(jedi);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final Long id) {
        Optional<Jedi> jediEntity = jediRepository.findById(id);

        if (jediEntity.isPresent()) {
            jediRepository.delete(jediEntity.get());

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}
