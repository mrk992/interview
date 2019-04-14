package com.ongres.app.repository;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
@Qualifier("InventoryRepository")
public class InventoryRepository implements IInventoryRepository {

    private final Connection connect;

    public InventoryRepository() {
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
    public boolean isAvailableInInventory(int id) {
        try {
            PreparedStatement pre = this.connect.prepareStatement("SELECT INVENTORY_IN_STOCK(?)");
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            boolean result = false;
            while (rs.next()) {
                result = Boolean.parseBoolean(rs.getString(1));
            }

            rs.close();
            pre.close();

            return result;
        } catch (Exception e) {
            System.err.println("\nError!");
            System.err.println("\nCause: " + e.getCause());
            System.err.println("\nMessage: " + e.getMessage());

            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public int getInventoryIdByName(String title) {
        try {
            PreparedStatement pre = this.connect.prepareStatement("SELECT * \n" +
                    "FROM inventory \n" +
                    "INNER JOIN film ON inventory.film_id = film.film_id \n" +
                    "WHERE title = ?");
            pre.setString(1, title);
            ResultSet rs = pre.executeQuery();
            int result = 0;
            while (rs.next()) {
                result = Integer.parseInt(rs.getString("film_id"));
            }

            rs.close();
            pre.close();

            return result;
        } catch (Exception e) {
            System.err.println("\nError!");
            System.err.println("\nCause: " + e.getCause());
            System.err.println("\nMessage: " + e.getMessage());

            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
