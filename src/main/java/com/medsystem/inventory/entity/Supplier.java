package com.medsystem.inventory.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a medicine supplier.
 * This class maps to the 'suppliers' table in the database.
 *
 * @author MedSystem
 * @version 1.0
 */
@Entity
@Table(name = "suppliers")
@Data
@NoArgsConstructor
public class Supplier {
    
    /**
     * Unique identifier for the supplier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the supplier.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Contact number of the supplier.
     */
    @Column(nullable = false)
    private String contactNumber;

    /**
     * Email address of the supplier.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Address of the supplier.
     */
    @Column(nullable = false)
    private String address;

    /**
     * List of medicines supplied by this supplier.
     */
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<Medicine> medicines = new ArrayList<>();
} 