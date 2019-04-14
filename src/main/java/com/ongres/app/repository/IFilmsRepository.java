package com.ongres.app.repository;

import com.ongres.app.entities.FilmByActor;

import java.util.List;

public interface IFilmsRepository {
    List<FilmByActor> getFilmsByActor(String firstName, String lastName);
}
