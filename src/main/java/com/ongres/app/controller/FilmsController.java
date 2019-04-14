package com.ongres.app.controller;

import com.ongres.app.entities.FilmsByActorTO;
import com.ongres.app.repository.IFilmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FilmsController {
    @Autowired
    @Qualifier("FilmsRepository")
    private IFilmsRepository filmsRepository;

    @RequestMapping("/films")
    public List<FilmsByActorTO> getFilmsByActor(@RequestParam(value = "actor") String actor,
                                                @RequestParam(value = "category", defaultValue = "New") String category) {
        List<FilmsByActorTO> films = new ArrayList<>();
        String names[] = actor.split(" ");
        if (names.length != 2) {
            FilmsByActorTO film = new FilmsByActorTO();
            film.setError("Actor not found");
            films.add(film);
            return films;
        }
        String firstName = names[0];
        String lastName = names[1];
        if (!actor.isEmpty()) {
            filmsRepository.getFilmsByActor(firstName, lastName).stream().filter(film -> film.getCategory().equals(category)).forEach(
                    filmByActor -> {
                        FilmsByActorTO film = new FilmsByActorTO();
                        film.setActorFirstName(filmByActor.getActorFirstName());
                        film.setActorLastName(filmByActor.getActorLastName());
                        film.setCategory(filmByActor.getCategory());
                        film.setDescription(filmByActor.getDescription());
                        film.setFilmTitle(filmByActor.getFilmTitle());
                        film.setError("");
                        films.add(film);
                    }
            );
        } else {
            FilmsByActorTO film = new FilmsByActorTO();
            film.setError("Actor not found");
            films.add(film);
        }
        return films;
    }
}
