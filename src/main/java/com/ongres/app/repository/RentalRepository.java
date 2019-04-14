package com.ongres.app.repository;


import com.ongres.app.entities.FilmByActor;
import com.ongres.app.entities.RentTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
@Qualifier("RentalRepository")
public class RentalRepository implements IRentalRepository{

    private final Connection connect;

    public RentalRepository() {
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
    public void saveRent(RentTO rent) {

        try {
            PreparedStatement pre = this.connect.prepareStatement("INSERT INTO rental (rental_date, inventory_id, customer_id, return_date, staff_id)\n" +
                    "VALUES (?, ?, ?, ?, ?)");
            //pre.setLong(1, rent.getTimestamp().getTime());
            pre.setTimestamp(1, rent.getTimestamp());
            pre.setInt(2, rent.getInventoryId());
            pre.setInt(3, rent.getCustomerId());
            pre.setTimestamp(4, rent.getTimestamp());
            pre.setInt(5, rent.getStaffId());
            ResultSet rs = pre.executeQuery();

            rs.close();
            pre.close();

        } catch (Exception e) {
            System.err.println("\nError!");
            System.err.println("\nCause: " + e.getCause());
            System.err.println("\nMessage: " + e.getMessage());

            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
