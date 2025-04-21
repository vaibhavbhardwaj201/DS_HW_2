package com.medsystem.inventory.service;

import com.medsystem.inventory.entity.Medicine;
import com.medsystem.inventory.model.xml.MedicineXml;
import jakarta.xml.bind.JAXBException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing medicines in the inventory system.
 * Provides methods for CRUD operations and additional functionality like XML export.
 */
public interface MedicineService {
    /**
     * Retrieves all medicines from the inventory.
     *
     * @return List of all medicines
     */
    List<Medicine> getAllMedicines();

    /**
     * Retrieves a medicine by its ID.
     *
     * @param id The ID of the medicine to retrieve
     * @return Optional containing the medicine if found, empty otherwise
     */
    Optional<Medicine> getMedicineById(Long id);

    /**
     * Creates a new medicine in the inventory.
     *
     * @param medicine The medicine to create
     * @return The created medicine with generated ID
     */
    Medicine createMedicine(Medicine medicine);

    /**
     * Updates an existing medicine in the inventory.
     *
     * @param id The ID of the medicine to update
     * @param medicine The updated medicine data
     * @return The updated medicine
     * @throws RuntimeException if the medicine is not found
     */
    Medicine updateMedicine(Long id, Medicine medicine);

    /**
     * Deletes a medicine from the inventory.
     *
     * @param id The ID of the medicine to delete
     */
    void deleteMedicine(Long id);

    /**
     * Searches for medicines by name (case-insensitive partial match).
     *
     * @param name The name to search for
     * @return List of medicines matching the search criteria
     */
    List<Medicine> searchMedicinesByName(String name);

    /**
     * Updates the stock quantity of a medicine.
     *
     * @param id The ID of the medicine
     * @param quantity The new quantity in stock
     * @return The updated medicine
     * @throws RuntimeException if the medicine is not found
     */
    Medicine updateStock(Long id, Integer quantity);

    /**
     * Exports medicines to XML format based on category and supplier filters.
     *
     * @param categoryId The ID of the category to filter by
     * @param supplierId The ID of the supplier to filter by
     * @return String containing the XML representation
     * @throws JAXBException if there is an error during XML conversion
     * @throws IOException if there is an error during file operations
     */
    String exportMedicinesToXml(Long categoryId, Long supplierId) throws JAXBException, IOException;

    /**
     * Converts a Medicine entity to its XML representation.
     *
     * @param medicine The medicine to convert
     * @return The XML representation of the medicine
     */
    MedicineXml convertToMedicineXml(Medicine medicine);
} 