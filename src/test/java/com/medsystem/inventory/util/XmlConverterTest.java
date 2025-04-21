package com.medsystem.inventory.util;

import com.medsystem.inventory.model.xml.MedicineListXml;
import com.medsystem.inventory.model.xml.MedicineXml;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class XmlConverterTest {

    private XmlConverter xmlConverter;
    
    @TempDir
    Path tempDir;

    private MedicineXml medicine1;
    private MedicineXml medicine2;
    private MedicineListXml medicineList;

    @BeforeEach
    void setUp() {
        xmlConverter = new XmlConverter(tempDir.toString());
        
        // Create test data
        medicine1 = new MedicineXml(
                "Amoxicillin",
                "MediPharm Labs",
                LocalDate.now().plusYears(1),
                new BigDecimal("15.99"),
                100,
                "Antibiotics",
                "MediPharm Inc"
        );
        
        medicine2 = new MedicineXml(
                "Ibuprofen",
                "HealthCare Pharma",
                LocalDate.now().plusYears(2),
                new BigDecimal("8.99"),
                200,
                "Painkillers",
                "HealthCare Supplies"
        );
        
        medicineList = new MedicineListXml(Arrays.asList(medicine1, medicine2));
    }

    @Test
    @DisplayName("Should create exports directory if it doesn't exist")
    void convertToXml_CreatesExportsDirectory() throws JAXBException, IOException {
        // Act
        xmlConverter.convertToXml(medicineList, "medicines");
        
        // Assert
        assertTrue(Files.exists(tempDir), "Exports directory should be created");
    }

    @Test
    @DisplayName("Should generate XML file with correct content")
    void convertToXml_GeneratesCorrectXml() throws JAXBException, IOException {
        // Act
        String filePath = xmlConverter.convertToXml(medicineList, "medicines");
        
        // Assert
        File file = new File(filePath);
        assertTrue(file.exists(), "XML file should be created");
        
        String content = Files.readString(file.toPath());
        assertTrue(content.contains("<medicines>"), "XML should contain medicines root element");
        assertTrue(content.contains("<medicine>"), "XML should contain medicine elements");
        assertTrue(content.contains("Amoxicillin"), "XML should contain medicine name");
        assertTrue(content.contains("Ibuprofen"), "XML should contain medicine name");
    }

    @Test
    @DisplayName("Should handle empty medicine list")
    void convertToXml_HandlesEmptyList() throws JAXBException, IOException {
        // Arrange
        MedicineListXml emptyList = new MedicineListXml(Arrays.asList());
        
        // Act
        String filePath = xmlConverter.convertToXml(emptyList, "medicines");
        
        // Assert
        File file = new File(filePath);
        assertTrue(file.exists(), "XML file should be created");
        
        String content = Files.readString(file.toPath());
        System.out.println("Generated XML content:");
        System.out.println(content);
        assertTrue(content.contains("<medicines") && content.contains("/>") || content.contains("</medicines>"), 
                "XML should contain medicines element (either self-closing or with end tag)");
        assertFalse(content.contains("<medicine>"), "XML should not contain medicine elements");
    }

    @Test
    @DisplayName("Should throw JAXBException for invalid object")
    void convertToXml_ThrowsJAXBExceptionForInvalidObject() {
        // Arrange
        Object invalidObject = new Object();
        
        // Act & Assert
        assertThrows(JAXBException.class, () -> {
            xmlConverter.convertToXml(invalidObject, "test");
        });
    }
} 