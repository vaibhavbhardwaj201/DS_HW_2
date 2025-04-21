package com.medsystem.inventory.model.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

/**
 * XML model class representing a list of medicines for XML serialization.
 * This class is used as a wrapper for serializing multiple MedicineXml objects.
 *
 * @author MedSystem
 * @version 1.0
 */
@XmlRootElement(name = "medicines")
@XmlAccessorType(XmlAccessType.FIELD)
public class MedicineListXml {
    
    /**
     * List of medicine XML objects.
     */
    @XmlElement(name = "medicine")
    private List<MedicineXml> medicines;

    /**
     * Default constructor required by JAXB.
     */
    public MedicineListXml() {
    }

    /**
     * Constructor with medicines list.
     *
     * @param medicines the list of medicine XML objects
     */
    public MedicineListXml(List<MedicineXml> medicines) {
        this.medicines = medicines;
    }

    /**
     * Gets the list of medicine XML objects.
     *
     * @return the list of medicine XML objects
     */
    public List<MedicineXml> getMedicines() {
        return medicines;
    }

    /**
     * Sets the list of medicine XML objects.
     *
     * @param medicines the list of medicine XML objects
     */
    public void setMedicines(List<MedicineXml> medicines) {
        this.medicines = medicines;
    }
} 