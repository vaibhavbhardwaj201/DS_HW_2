package com.medsystem.inventory.controller;

import com.medsystem.inventory.service.MedicineService;
import jakarta.xml.bind.JAXBException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/medicines")
@RequiredArgsConstructor
public class MedicineController {
    private final MedicineService medicineService;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Medicine Inventory Service is working correctly!");
    }

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
    
    @GetMapping("/xml/download")
    public ResponseEntity<Resource> downloadXmlFile(
            @RequestParam(required = false) Long category,
            @RequestParam(required = false) Long supplier) {
        try {
            String filePath = medicineService.exportMedicinesToXml(category, supplier);
            File file = new File(filePath);
            
            if (!file.exists()) {
                return ResponseEntity.notFound().build();
            }
            
            Resource resource = new FileSystemResource(file);
            
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_XML)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                    .body(resource);
        } catch (JAXBException | IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    @GetMapping("/xml/content")
    public ResponseEntity<String> getXmlContent(
            @RequestParam(required = false) Long category,
            @RequestParam(required = false) Long supplier) {
        try {
            String filePath = medicineService.exportMedicinesToXml(category, supplier);
            Path path = Paths.get(filePath);
            String content = new String(Files.readAllBytes(path));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_XML)
                    .body(content);
        } catch (JAXBException | IOException e) {
            return ResponseEntity.internalServerError()
                    .body("Error generating XML content: " + e.getMessage());
        }
    }
} 