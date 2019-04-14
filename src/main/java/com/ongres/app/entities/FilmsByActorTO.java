package com.ongres.app.entities;

public class FilmsByActorTO {
    String actorFirstName;
    String actorLastName;
    String filmTitle;
    String description;
    String category;
    String error;

    public String getActorFirstName() {
        return actorFirstName;
    }

    public void setActorFirstName(String actorFirstName) {
        this.actorFirstName = actorFirstName;
    }

    public String getActorLastName() {
        return actorLastName;
    }

    public void setActorLastName(String actorLastName) {
        this.actorLastName = actorLastName;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
