package com.medsystem.inventory.model.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * XML model class representing a medicine for XML serialization.
 * This class is used to convert Medicine entities to XML format.
 *
 * @author MedSystem
 * @version 1.0
 */
@XmlRootElement(name = "medicine")
@XmlAccessorType(XmlAccessType.FIELD)
public class MedicineXml {
    
    /**
     * Name of the medicine.
     */
    @XmlElement
    private String name;

    /**
     * Manufacturer of the medicine.
     */
    @XmlElement
    private String manufacturer;

    /**
     * Expiry date of the medicine.
     */
    @XmlElement
    private LocalDate expiryDate;

    /**
     * Price of the medicine.
     */
    @XmlElement
    private BigDecimal price;

    /**
     * Quantity of the medicine currently in stock.
     */
    @XmlElement
    private Integer quantityInStock;

    /**
     * Name of the category to which the medicine belongs.
     */
    @XmlElement
    private String category;

    /**
     * Name of the supplier of the medicine.
     */
    @XmlElement
    private String supplier;

    /**
     * Default constructor required by JAXB.
     */
    public MedicineXml() {
    }

    /**
     * Constructor with all fields.
     *
     * @param name            the name of the medicine
     * @param manufacturer    the manufacturer of the medicine
     * @param expiryDate      the expiry date of the medicine
     * @param price           the price of the medicine
     * @param quantityInStock the quantity of the medicine in stock
     * @param category        the name of the category
     * @param supplier        the name of the supplier
     */
    public MedicineXml(String name, String manufacturer, LocalDate expiryDate, 
                      BigDecimal price, Integer quantityInStock, String category, String supplier) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.expiryDate = expiryDate;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.category = category;
        this.supplier = supplier;
    }

    /**
     * Gets the name of the medicine.
     *
     * @return the name of the medicine
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the medicine.
     *
     * @param name the name of the medicine
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the manufacturer of the medicine.
     *
     * @return the manufacturer of the medicine
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Sets the manufacturer of the medicine.
     *
     * @param manufacturer the manufacturer of the medicine
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Gets the expiry date of the medicine.
     *
     * @return the expiry date of the medicine
     */
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the expiry date of the medicine.
     *
     * @param expiryDate the expiry date of the medicine
     */
    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * Gets the price of the medicine.
     *
     * @return the price of the medicine
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the price of the medicine.
     *
     * @param price the price of the medicine
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets the quantity of the medicine in stock.
     *
     * @return the quantity of the medicine in stock
     */
    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    /**
     * Sets the quantity of the medicine in stock.
     *
     * @param quantityInStock the quantity of the medicine in stock
     */
    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    /**
     * Gets the name of the category.
     *
     * @return the name of the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the name of the category.
     *
     * @param category the name of the category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Gets the name of the supplier.
     *
     * @return the name of the supplier
     */
    public String getSupplier() {
        return supplier;
    }

    /**
     * Sets the name of the supplier.
     *
     * @param supplier the name of the supplier
     */
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
} 