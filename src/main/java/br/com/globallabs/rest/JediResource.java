package br.com.globallabs.rest;

import br.com.globallabs.model.Jedi;
import br.com.globallabs.service.JediService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/jedis")
public class JediResource {

    @Autowired
    private JediService service;

    @GetMapping
    public List<Jedi> listAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jedi> searchId(@PathVariable("id") Long id) {
        var jedi = service.findById(id);
        return ResponseEntity.ok(jedi);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Jedi create(@Valid @RequestBody Jedi jedi) {
        return service.save(jedi);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jedi> update(@PathVariable("id") Long id, @Valid @RequestBody Jedi dto) {
        var jedi = service.update(id, dto);
        return ResponseEntity.ok(jedi);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
