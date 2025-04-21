package com.medsystem.inventory.model.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * XML model class representing a supplier for XML serialization.
 * This class is used to serialize and deserialize supplier data to/from XML format.
 *
 * @author MedSystem
 * @version 1.0
 */
@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierXml {
    
    /**
     * The unique identifier of the supplier.
     */
    @XmlElement(name = "id")
    private Long id;

    /**
     * The name of the supplier.
     */
    @XmlElement(name = "name")
    private String name;

    /**
     * The contact number of the supplier.
     */
    @XmlElement(name = "contactNumber")
    private String contactNumber;

    /**
     * The email address of the supplier.
     */
    @XmlElement(name = "email")
    private String email;

    /**
     * The address of the supplier.
     */
    @XmlElement(name = "address")
    private String address;

    /**
     * Default constructor required by JAXB.
     */
    public SupplierXml() {
    }

    /**
     * Constructor with all fields.
     *
     * @param id the supplier's unique identifier
     * @param name the supplier's name
     * @param contactNumber the supplier's contact number
     * @param email the supplier's email address
     * @param address the supplier's address
     */
    public SupplierXml(Long id, String name, String contactNumber, String email, String address) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
        this.address = address;
    }

    /**
     * Gets the supplier's unique identifier.
     *
     * @return the supplier's ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the supplier's unique identifier.
     *
     * @param id the supplier's ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the supplier's name.
     *
     * @return the supplier's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the supplier's name.
     *
     * @param name the supplier's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the supplier's contact number.
     *
     * @return the supplier's contact number
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * Sets the supplier's contact number.
     *
     * @param contactNumber the supplier's contact number
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * Gets the supplier's email address.
     *
     * @return the supplier's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the supplier's email address.
     *
     * @param email the supplier's email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the supplier's address.
     *
     * @return the supplier's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the supplier's address.
     *
     * @param address the supplier's address
     */
    public void setAddress(String address) {
        this.address = address;
    }
} 