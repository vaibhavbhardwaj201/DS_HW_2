package com.medsystem.inventory.util;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class XmlConverter {
    private static final Logger logger = LoggerFactory.getLogger(XmlConverter.class);
    private static final String DEFAULT_EXPORT_DIR = "exports";
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = 
            DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            
    private final String exportDir;
    
    public XmlConverter() {
        this(DEFAULT_EXPORT_DIR);
    }
    
    public XmlConverter(String exportDir) {
        this.exportDir = exportDir;
        // Create the exports directory during initialization
        createExportDirectory();
    }
    
    private void createExportDirectory() {
        try {
            Path exportPath = Paths.get(exportDir);
            if (!Files.exists(exportPath)) {
                Files.createDirectories(exportPath);
                logger.info("Created exports directory at: {}", exportPath.toAbsolutePath());
            }
        } catch (IOException e) {
            logger.error("Failed to create exports directory: {}", e.getMessage());
        }
    }

    public <T> String convertToXml(T object, String filenamePrefix) throws JAXBException, IOException {
        // Ensure the exports directory exists
        Path exportPath = Paths.get(exportDir);
        if (!Files.exists(exportPath)) {
            Files.createDirectories(exportPath);
            logger.info("Created exports directory at: {}", exportPath.toAbsolutePath());
        }

        // Generate filename with timestamp
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
        String filename = String.format("%s_%s.xml", filenamePrefix, timestamp);
        Path filePath = exportPath.resolve(filename);
        File outputFile = filePath.toFile();
        
        logger.info("Generating XML file at: {}", outputFile.getAbsolutePath());

        // Create JAXB context and marshaller
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Marshal the object to XML
        marshaller.marshal(object, outputFile);
        
        logger.info("XML file generated successfully at: {}", outputFile.getAbsolutePath());

        return outputFile.getAbsolutePath();
    }
} 