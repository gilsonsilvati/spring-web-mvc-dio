package br.com.globallabs.repository;

import br.com.globallabs.model.Jedi;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JediRepository {

    private List<Jedi> jedis;

    public JediRepository() {
        var luke = new Jedi("Luke", "Skywalker");
        var ben = new Jedi("Ben", "Solo");

        jedis = new ArrayList<>();
        jedis.add(luke);
        jedis.add(ben);
    }

    public List<Jedi> getAllJedi() {
        return jedis;
    }

    public void add(final Jedi jedi) {
        jedis.add(jedi);
    }

}
