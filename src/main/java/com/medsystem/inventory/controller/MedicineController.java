package com.medsystem.inventory.controller;

import com.medsystem.inventory.service.MedicineService;
import jakarta.xml.bind.JAXBException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/medicines")
@RequiredArgsConstructor
public class MedicineController {
    private final MedicineService medicineService;

    @GetMapping("/xml")
    public ResponseEntity<String> exportMedicinesToXml(
            @RequestParam(required = false) Long category,
            @RequestParam(required = false) Long supplier) {
        try {
            String filePath = medicineService.exportMedicinesToXml(category, supplier);
            return ResponseEntity.ok("XML file generated successfully at: " + filePath);
        } catch (JAXBException | IOException e) {
            return ResponseEntity.internalServerError()
                    .body("Error generating XML file: " + e.getMessage());
        }
    }
} 