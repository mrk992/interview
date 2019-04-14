package com.ongres.app.repository;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
@Qualifier("StaffRepository")
public class StaffRepository implements IStaffRepository {

    private final Connection connect;

    public StaffRepository() {
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
    public int getStaffByName(String firstName, String lastName) {
        try {
            PreparedStatement pre = this.connect.prepareStatement("SELECT staff_id \n" +
                    "FROM staff \n" +
                    "WHERE first_name = ?\n" +
                    "  AND last_name = ?");
            pre.setString(1, firstName);
            pre.setString(2, lastName);
            ResultSet rs = pre.executeQuery();
            int result = 0;
            while (rs.next()) {
                result = Integer.parseInt(rs.getString(1));
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
