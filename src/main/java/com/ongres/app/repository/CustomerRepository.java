package com.ongres.app.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("CustomerRepository")
public class CustomerRepository implements ICustomerRepository {
    private final Connection connect;

    public CustomerRepository() {
        String url = "jdbc:postgresql://localhost/";
        String nameDb = "sakila";
        String driver = "org.postgresql.Driver";
        String userName = "sakila";
        String password = "sakila";

        try {
            Class.forName(driver);
            this.connect = DriverManager.getConnection(url + nameDb, userName, password);
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int countCustomersByCountry(String country) {
        try {
            PreparedStatement pre = this.connect.prepareStatement("SELECT Count(*) \n" +
                    "FROM customer_list \n" +
                    "WHERE country = ? ;");
            pre.setString(1,country);
            ResultSet rs = pre.executeQuery();
            int result = 0;
            while(rs.next()) {
                result = Integer.parseInt(rs.getString(1));
            }

            rs.close();
            pre.close();

            return result;
        }catch (Exception e) {
            System.err.println("\nError!");
            System.err.println("\nCause: " +e.getCause());
            System.err.println("\nMessage: " +e.getMessage());

            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countCustomersByCity(String city) {
        try {
            PreparedStatement pre = this.connect.prepareStatement("SELECT Count(*) \n" +
                    "FROM customer_list \n" +
                    "WHERE city = ? ;");
            pre.setString(1,city);
            ResultSet rs = pre.executeQuery();
            int result = 0;
            while(rs.next()) {
                result = Integer.parseInt(rs.getString(1));
            }

            rs.close();
            pre.close();

            return result;
        }catch (Exception e) {
            System.err.println("\nError!");
            System.err.println("\nCause: " +e.getCause());
            System.err.println("\nMessage: " +e.getMessage());

            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}