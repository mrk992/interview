package com.ongres.app.repository;

import com.ongres.app.entities.FilmByActor;

import java.util.List;

public interface IFilmsRepository {
    /**
     * Get all films by a actor
     * @param firstName of the actor
     * @param lastName of the actor
     * @return list of films by actor
     */
    List<FilmByActor> getFilmsByActor(String firstName, String lastName);
}
