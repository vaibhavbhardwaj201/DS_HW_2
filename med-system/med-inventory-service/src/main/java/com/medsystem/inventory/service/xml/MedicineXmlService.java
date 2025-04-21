package com.medsystem.inventory.service.xml;

import com.medsystem.inventory.entity.Medicine;
import com.medsystem.inventory.model.xml.MedicineXml;
import com.medsystem.inventory.model.xml.MedicineListXml;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of XmlService for Medicine entities.
 * Handles XML serialization and deserialization for Medicine objects.
 */
@Service
public class MedicineXmlService implements XmlService<Medicine, MedicineXml> {

    @Override
    public MedicineXml toXml(Medicine medicine) {
        return new MedicineXml(
                medicine.getName(),
                medicine.getManufacturer(),
                medicine.getExpiryDate(),
                medicine.getPrice(),
                medicine.getQuantityInStock(),
                medicine.getCategory().getName(),
                medicine.getSupplier().getName()
        );
    }

    @Override
    public Medicine fromXml(MedicineXml xml) {
        // This would require additional services to look up Category and Supplier
        throw new UnsupportedOperationException("Conversion from XML to Medicine requires additional services");
    }

    @Override
    public List<MedicineXml> toXmlList(List<Medicine> entities) {
        return entities.stream()
                .map(this::toXml)
                .collect(Collectors.toList());
    }

    @Override
    public List<Medicine> fromXmlList(List<MedicineXml> xmlList) {
        throw new UnsupportedOperationException("Conversion from XML list to Medicine list requires additional services");
    }

    @Override
    public void serializeToXml(Medicine entity, OutputStream outputStream) {
        try {
            MedicineXml medicineXml = toXml(entity);
            JAXBContext context = JAXBContext.newInstance(MedicineXml.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(medicineXml, outputStream);
        } catch (JAXBException e) {
            throw new RuntimeException("Failed to serialize medicine to XML", e);
        }
    }

    @Override
    public Medicine deserializeFromXml(InputStream inputStream) {
        try {
            JAXBContext context = JAXBContext.newInstance(MedicineXml.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            MedicineXml medicineXml = (MedicineXml) unmarshaller.unmarshal(inputStream);
            return fromXml(medicineXml);
        } catch (JAXBException e) {
            throw new RuntimeException("Failed to deserialize medicine from XML", e);
        }
    }

    /**
     * Serializes a list of medicines to XML.
     *
     * @param medicines the list of medicines to serialize
     * @param outputStream the output stream to write to
     */
    public void serializeListToXml(List<Medicine> medicines, OutputStream outputStream) {
        try {
            MedicineListXml medicineListXml = new MedicineListXml(toXmlList(medicines));
            JAXBContext context = JAXBContext.newInstance(MedicineListXml.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(medicineListXml, outputStream);
        } catch (JAXBException e) {
            throw new RuntimeException("Failed to serialize medicine list to XML", e);
        }
    }
} 