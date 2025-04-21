package com.medsystem.inventory.service.impl;

import com.medsystem.inventory.entity.Medicine;
import com.medsystem.inventory.model.xml.MedicineListXml;
import com.medsystem.inventory.model.xml.MedicineXml;
import com.medsystem.inventory.repository.MedicineRepository;
import com.medsystem.inventory.service.MedicineService;
import com.medsystem.inventory.util.XmlConverter;
import jakarta.xml.bind.JAXBException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of the MedicineService interface.
 * Provides concrete implementations for medicine-related operations.
 */
@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {
    private final MedicineRepository medicineRepository;
    private final XmlConverter xmlConverter;

    @Override
    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

    @Override
    public Optional<Medicine> getMedicineById(Long id) {
        return medicineRepository.findById(id);
    }

    @Override
    public Medicine createMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    @Override
    public Medicine updateMedicine(Long id, Medicine medicine) {
        Medicine existingMedicine = getMedicineById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found with id: " + id));
        
        existingMedicine.setName(medicine.getName());
        existingMedicine.setManufacturer(medicine.getManufacturer());
        existingMedicine.setExpiryDate(medicine.getExpiryDate());
        existingMedicine.setPrice(medicine.getPrice());
        existingMedicine.setQuantityInStock(medicine.getQuantityInStock());
        existingMedicine.setCategory(medicine.getCategory());
        existingMedicine.setSupplier(medicine.getSupplier());
        
        return medicineRepository.save(existingMedicine);
    }

    @Override
    public void deleteMedicine(Long id) {
        medicineRepository.deleteById(id);
    }

    @Override
    public List<Medicine> searchMedicinesByName(String name) {
        return medicineRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Medicine updateStock(Long id, Integer quantity) {
        Medicine medicine = getMedicineById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found with id: " + id));
        medicine.setQuantityInStock(quantity);
        return medicineRepository.save(medicine);
    }

    @Override
    public String exportMedicinesToXml(Long categoryId, Long supplierId) throws JAXBException, IOException {
        List<Medicine> medicines = medicineRepository.findByCategoryIdAndSupplierId(categoryId, supplierId);
        
        List<MedicineXml> medicineXmlList = medicines.stream()
                .map(this::convertToMedicineXml)
                .collect(Collectors.toList());
        
        MedicineListXml medicineListXml = new MedicineListXml(medicineXmlList);
        
        return xmlConverter.convertToXml(medicineListXml, "medicines");
    }
    
    @Override
    public MedicineXml convertToMedicineXml(Medicine medicine) {
        return new MedicineXml(
                medicine.getName(),
                medicine.getManufacturer(),
                medicine.getExpiryDate(),
                medicine.getPrice(),
                medicine.getQuantityInStock(),
                medicine.getCategoryName(),
                medicine.getSupplierName()
        );
    }
} 