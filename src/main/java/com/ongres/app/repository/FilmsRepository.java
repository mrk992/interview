package com.ongres.app.repository;

import com.ongres.app.entities.FilmByActor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
@Qualifier("FilmsRepository")
public class FilmsRepository implements IFilmsRepository {
    private final Connection connect;

    public FilmsRepository() {
        String url = "jdbc:postgresql://localhost/";
        String nameDb = "sakila";
        String driver = "org.postgresql.Driver";
        String userName = "sakila";
        String password = "sakila";

        try {
            Class.forName(driver);
            this.connect = DriverManager.getConnection(url + nameDb, userName, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FilmByActor> getFilmsByActor(String firstName, String lastName) {
        try {
            PreparedStatement pre = this.connect.prepareStatement("SELECT *\n" +
                    "  FROM film_actor\n" +
                    "  INNER JOIN film \n" +
                    "  ON film_actor.film_id = film.film_id\n" +
                    "  INNER JOIN actor\n" +
                    "  on film_actor.actor_id = actor.actor_id\n" +
                    "  INNER JOIN film_category\n" +
                    "  on film_actor.film_id = film_category.film_id\n" +
                    "  INNER JOIN category\n" +
                    "  on film_category.category_id = category.category_id" +
                    "  WHERE first_name = ? AND last_name = ?");
            pre.setString(1, firstName);
            pre.setString(2, lastName);
            ResultSet rs = pre.executeQuery();
            List<FilmByActor> films = new ArrayList<>();
            while (rs.next()) {
                FilmByActor film = new FilmByActor();
                film.setActorFirstName(rs.getString("first_name"));
                film.setActorLastName(rs.getString("last_name"));
                film.setFilmTitle(rs.getString("title"));
                film.setDescription(rs.getString("description"));
                film.setCategory(rs.getString("name"));
                films.add(film);
            }

            rs.close();
            pre.close();

            return films;
        } catch (Exception e) {
            System.err.println("\nError!");
            System.err.println("\nCause: " + e.getCause());
            System.err.println("\nMessage: " + e.getMessage());

            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
