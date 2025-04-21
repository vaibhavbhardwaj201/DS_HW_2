package com.medsystem.inventory.controller;

import com.medsystem.inventory.service.MedicineService;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedicineControllerTest {

    @Mock
    private MedicineService medicineService;

    @InjectMocks
    private MedicineController medicineController;

    private String expectedFilePath;

    @BeforeEach
    void setUp() {
        expectedFilePath = "/path/to/exports/medicines_20240321_143022.xml";
    }

    @Test
    @DisplayName("Should return success response when XML export is successful")
    void exportMedicinesToXml_Success() throws JAXBException, IOException {
        // Arrange
        Long category = 1L;
        Long supplier = 1L;
        when(medicineService.exportMedicinesToXml(category, supplier)).thenReturn(expectedFilePath);

        // Act
        ResponseEntity<String> response = medicineController.exportMedicinesToXml(category, supplier);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains(expectedFilePath));
        verify(medicineService).exportMedicinesToXml(category, supplier);
    }

    @Test
    @DisplayName("Should return success response when no filters are provided")
    void exportMedicinesToXml_NoFilters() throws JAXBException, IOException {
        // Arrange
        when(medicineService.exportMedicinesToXml(null, null)).thenReturn(expectedFilePath);

        // Act
        ResponseEntity<String> response = medicineController.exportMedicinesToXml(null, null);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains(expectedFilePath));
        verify(medicineService).exportMedicinesToXml(null, null);
    }

    @Test
    @DisplayName("Should handle JAXBException during XML export")
    void exportMedicinesToXml_JAXBException() throws JAXBException, IOException {
        // Arrange
        Long category = 1L;
        Long supplier = 1L;
        String errorMessage = "JAXB error occurred";
        when(medicineService.exportMedicinesToXml(category, supplier))
                .thenThrow(new JAXBException(errorMessage));

        // Act
        ResponseEntity<String> response = medicineController.exportMedicinesToXml(category, supplier);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody().contains("Error generating XML file"));
        assertTrue(response.getBody().contains(errorMessage));
        verify(medicineService).exportMedicinesToXml(category, supplier);
    }

    @Test
    @DisplayName("Should handle IOException during XML export")
    void exportMedicinesToXml_IOException() throws JAXBException, IOException {
        // Arrange
        Long category = 1L;
        Long supplier = 1L;
        String errorMessage = "IO error occurred";
        when(medicineService.exportMedicinesToXml(category, supplier))
                .thenThrow(new IOException(errorMessage));

        // Act
        ResponseEntity<String> response = medicineController.exportMedicinesToXml(category, supplier);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody().contains("Error generating XML file"));
        assertTrue(response.getBody().contains(errorMessage));
        verify(medicineService).exportMedicinesToXml(category, supplier);
    }
} 