package com.medsystem.inventory.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a medicine category.
 * This class maps to the 'categories' table in the database.
 *
 * @author MedSystem
 * @version 1.0
 */
@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
public class Category {
    
    /**
     * Unique identifier for the category.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the category.
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * Description of the category.
     */
    @Column
    private String description;

    /**
     * List of medicines in this category.
     */
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Medicine> medicines = new ArrayList<>();
} 