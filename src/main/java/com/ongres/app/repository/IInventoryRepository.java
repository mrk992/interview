package com.ongres.app.repository;

public interface IInventoryRepository {

    /**
     * Check if the film is available
     * @param id of title to check
     * @return true if the film is available
     */
    boolean isAvailableInInventory(int id);

    /**
     * Get ID of film by title
     * @param title
     * @return id of the film
     */
    int getInventoryIdByName(String title);
}
