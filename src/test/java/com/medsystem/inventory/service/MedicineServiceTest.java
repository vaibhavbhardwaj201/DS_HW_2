package com.medsystem.inventory.service;

import com.medsystem.inventory.entity.Category;
import com.medsystem.inventory.entity.Medicine;
import com.medsystem.inventory.entity.Supplier;
import com.medsystem.inventory.model.xml.MedicineListXml;
import com.medsystem.inventory.model.xml.MedicineXml;
import com.medsystem.inventory.repository.MedicineRepository;
import com.medsystem.inventory.service.impl.MedicineServiceImpl;
import com.medsystem.inventory.util.XmlConverter;
import jakarta.xml.bind.JAXBException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedicineServiceTest {

    @Mock
    private MedicineRepository medicineRepository;

    @Mock
    private XmlConverter xmlConverter;

    @InjectMocks
    private MedicineServiceImpl medicineService;

    private Medicine medicine1;
    private Medicine medicine2;
    private List<Medicine> medicines;
    private String expectedFilePath;

    @BeforeEach
    void setUp() {
        // Create test data
        Category category = new Category();
        category.setId(1L);
        category.setName("Antibiotics");

        Supplier supplier = new Supplier();
        supplier.setId(1L);
        supplier.setName("MediPharm Inc");

        medicine1 = new Medicine();
        medicine1.setId(1L);
        medicine1.setName("Amoxicillin");
        medicine1.setManufacturer("MediPharm Labs");
        medicine1.setExpiryDate(LocalDate.now().plusYears(1));
        medicine1.setPrice(new BigDecimal("15.99"));
        medicine1.setQuantityInStock(100);
        medicine1.setCategory(category);
        medicine1.setSupplier(supplier);

        medicine2 = new Medicine();
        medicine2.setId(2L);
        medicine2.setName("Ibuprofen");
        medicine2.setManufacturer("HealthCare Pharma");
        medicine2.setExpiryDate(LocalDate.now().plusYears(2));
        medicine2.setPrice(new BigDecimal("8.99"));
        medicine2.setQuantityInStock(200);
        medicine2.setCategory(category);
        medicine2.setSupplier(supplier);

        medicines = Arrays.asList(medicine1, medicine2);
        expectedFilePath = "/path/to/exports/medicines_20240321_143022.xml";
    }

    @Test
    @DisplayName("Should export medicines to XML when category and supplier are provided")
    void exportMedicinesToXml_WithCategoryAndSupplier() throws JAXBException, IOException {
        // Arrange
        Long categoryId = 1L;
        Long supplierId = 1L;
        when(medicineRepository.findByCategoryIdAndSupplierId(categoryId, supplierId)).thenReturn(medicines);
        when(xmlConverter.convertToXml(any(MedicineListXml.class), eq("medicines"))).thenReturn(expectedFilePath);

        // Act
        String result = medicineService.exportMedicinesToXml(categoryId, supplierId);

        // Assert
        assertEquals(expectedFilePath, result);
        verify(medicineRepository).findByCategoryIdAndSupplierId(categoryId, supplierId);
        verify(xmlConverter).convertToXml(any(MedicineListXml.class), eq("medicines"));
    }

    @Test
    @DisplayName("Should export medicines to XML when only category is provided")
    void exportMedicinesToXml_WithCategoryOnly() throws JAXBException, IOException {
        // Arrange
        Long categoryId = 1L;
        Long supplierId = null;
        when(medicineRepository.findByCategoryIdAndSupplierId(categoryId, supplierId)).thenReturn(medicines);
        when(xmlConverter.convertToXml(any(MedicineListXml.class), eq("medicines"))).thenReturn(expectedFilePath);

        // Act
        String result = medicineService.exportMedicinesToXml(categoryId, supplierId);

        // Assert
        assertEquals(expectedFilePath, result);
        verify(medicineRepository).findByCategoryIdAndSupplierId(categoryId, supplierId);
        verify(xmlConverter).convertToXml(any(MedicineListXml.class), eq("medicines"));
    }

    @Test
    @DisplayName("Should export medicines to XML when only supplier is provided")
    void exportMedicinesToXml_WithSupplierOnly() throws JAXBException, IOException {
        // Arrange
        Long categoryId = null;
        Long supplierId = 1L;
        when(medicineRepository.findByCategoryIdAndSupplierId(categoryId, supplierId)).thenReturn(medicines);
        when(xmlConverter.convertToXml(any(MedicineListXml.class), eq("medicines"))).thenReturn(expectedFilePath);

        // Act
        String result = medicineService.exportMedicinesToXml(categoryId, supplierId);

        // Assert
        assertEquals(expectedFilePath, result);
        verify(medicineRepository).findByCategoryIdAndSupplierId(categoryId, supplierId);
        verify(xmlConverter).convertToXml(any(MedicineListXml.class), eq("medicines"));
    }

    @Test
    @DisplayName("Should export all medicines to XML when no filters are provided")
    void exportMedicinesToXml_WithNoFilters() throws JAXBException, IOException {
        // Arrange
        Long categoryId = null;
        Long supplierId = null;
        when(medicineRepository.findByCategoryIdAndSupplierId(categoryId, supplierId)).thenReturn(medicines);
        when(xmlConverter.convertToXml(any(MedicineListXml.class), eq("medicines"))).thenReturn(expectedFilePath);

        // Act
        String result = medicineService.exportMedicinesToXml(categoryId, supplierId);

        // Assert
        assertEquals(expectedFilePath, result);
        verify(medicineRepository).findByCategoryIdAndSupplierId(categoryId, supplierId);
        verify(xmlConverter).convertToXml(any(MedicineListXml.class), eq("medicines"));
    }

    @Test
    @DisplayName("Should handle empty medicine list")
    void exportMedicinesToXml_WithEmptyList() throws JAXBException, IOException {
        // Arrange
        Long categoryId = 1L;
        Long supplierId = 1L;
        List<Medicine> emptyList = Arrays.asList();
        when(medicineRepository.findByCategoryIdAndSupplierId(categoryId, supplierId)).thenReturn(emptyList);
        when(xmlConverter.convertToXml(any(MedicineListXml.class), eq("medicines"))).thenReturn(expectedFilePath);

        // Act
        String result = medicineService.exportMedicinesToXml(categoryId, supplierId);

        // Assert
        assertEquals(expectedFilePath, result);
        verify(medicineRepository).findByCategoryIdAndSupplierId(categoryId, supplierId);
        verify(xmlConverter).convertToXml(any(MedicineListXml.class), eq("medicines"));
    }
} 