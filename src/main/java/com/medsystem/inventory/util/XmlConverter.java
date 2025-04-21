package com.medsystem.inventory.util;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
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
    private static final String DEFAULT_EXPORT_DIR = "exports";
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = 
            DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            
    private final String exportDir;
    
    public XmlConverter() {
        this(DEFAULT_EXPORT_DIR);
    }
    
    public XmlConverter(String exportDir) {
        this.exportDir = exportDir;
    }

    public <T> String convertToXml(T object, String filenamePrefix) throws JAXBException, IOException {
        // Create exports directory if it doesn't exist
        Path exportPath = Paths.get(exportDir);
        if (!Files.exists(exportPath)) {
            Files.createDirectories(exportPath);
        }

        // Generate filename with timestamp
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
        String filename = String.format("%s_%s.xml", filenamePrefix, timestamp);
        File outputFile = exportPath.resolve(filename).toFile();

        // Create JAXB context and marshaller
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        // Marshal the object to XML
        marshaller.marshal(object, outputFile);

        return outputFile.getAbsolutePath();
    }
} 