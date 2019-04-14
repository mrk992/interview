package com.ongres.app.repository;

public interface IStaffRepository {
    /**
     * retrieve a id of the staff by name and surname
     * @param firstName name of staff
     * @param lastName surname of staff
     * @return id of staff
     */
    int getStaffByName(String firstName, String lastName);
}
