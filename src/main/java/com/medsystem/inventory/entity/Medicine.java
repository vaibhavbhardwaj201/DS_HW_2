package com.medsystem.inventory.entity;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entity class representing a medicine in the inventory system.
 * This class maps to the 'medicines' table in the database and contains
 * information about a specific medicine including its name, manufacturer,
 * expiry date, price, and stock quantity.
 *
 * @author MedSystem
 * @version 1.0
 */
@Entity
@Table(name = "medicines")
@Data
@NoArgsConstructor
@XmlRootElement(name = "medicine")
@XmlAccessorType(XmlAccessType.FIELD)
public class Medicine {
    
    /**
     * Unique identifier for the medicine.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlTransient
    private Long id;

    /**
     * Name of the medicine.
     */
    @Column(nullable = false)
    @XmlElement
    private String name;

    /**
     * Manufacturer of the medicine.
     */
    @Column(nullable = false)
    @XmlElement
    private String manufacturer;

    /**
     * Expiry date of the medicine.
     */
    @Column(nullable = false)
    @XmlElement
    private LocalDate expiryDate;

    /**
     * Price of the medicine.
     */
    @Column(nullable = false)
    @XmlElement
    private BigDecimal price;

    /**
     * Quantity of the medicine currently in stock.
     */
    @Column(nullable = false)
    @XmlElement
    private Integer quantityInStock;

    /**
     * Category to which the medicine belongs.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @XmlTransient
    private Category category;

    /**
     * Supplier of the medicine.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    @XmlTransient
    private Supplier supplier;

    /**
     * Gets the name of the category for XML serialization.
     *
     * @return the name of the category
     */
    @XmlElement(name = "category")
    public String getCategoryName() {
        return category != null ? category.getName() : null;
    }

    /**
     * Gets the name of the supplier for XML serialization.
     *
     * @return the name of the supplier
     */
    @XmlElement(name = "supplier")
    public String getSupplierName() {
        return supplier != null ? supplier.getName() : null;
    }
} 