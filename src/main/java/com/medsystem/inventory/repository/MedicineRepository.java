package com.medsystem.inventory.repository;

import com.medsystem.inventory.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Medicine entities.
 * Provides database operations for medicines.
 */
@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    
    /**
     * Finds medicines by category and supplier IDs.
     *
     * @param categoryId the category ID
     * @param supplierId the supplier ID
     * @return list of medicines matching the criteria
     */
    @Query("SELECT m FROM Medicine m WHERE (:categoryId IS NULL OR m.category.id = :categoryId) " +
           "AND (:supplierId IS NULL OR m.supplier.id = :supplierId)")
    List<Medicine> findByCategoryIdAndSupplierId(@Param("categoryId") Long categoryId, 
                                               @Param("supplierId") Long supplierId);

    /**
     * Finds medicines by name containing the given string (case-insensitive).
     *
     * @param name the name to search for
     * @return list of medicines matching the name
     */
    List<Medicine> findByNameContainingIgnoreCase(String name);
} 