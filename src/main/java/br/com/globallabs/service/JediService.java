package br.com.globallabs.service;

import br.com.globallabs.exception.JediNotFoundException;
import br.com.globallabs.model.Jedi;
import br.com.globallabs.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JediService {

    @Autowired
    private JediRepository repository;

    public List<Jedi> findAll() {
        return repository.findAll();
    }

    public Jedi findById(Long id) {
        Optional<Jedi> jedi = repository.findById(id);

        if (jedi.isPresent()) {
            return jedi.get();
        }

        throw new JediNotFoundException();
    }

    public Jedi save(Jedi jedi) {
        return repository.save(jedi);
    }

    public Jedi update(Long id, Jedi dto) {
        Optional<Jedi> jediEntity = repository.findById(id);

        if (jediEntity.isPresent()) {
            var jedi = jediEntity.get();
            jedi.setName(dto.getName());
            jedi.setLastName(dto.getLastName());

            return repository.save(jedi);
        }

        throw new JediNotFoundException();
    }

    public void delete(Long id) {
        var jedi = findById(id);
        repository.delete(jedi);
    }

}
