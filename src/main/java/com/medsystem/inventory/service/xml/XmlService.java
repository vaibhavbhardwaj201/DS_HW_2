package com.medsystem.inventory.service.xml;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Generic interface for XML operations.
 * Provides basic XML serialization and deserialization capabilities.
 *
 * @param <T> The type of entity this service handles
 * @param <X> The type of XML model this service handles
 */
public interface XmlService<T, X> {
    
    /**
     * Converts an entity to its XML representation.
     *
     * @param entity the entity to convert
     * @return the XML representation of the entity
     */
    X toXml(T entity);

    /**
     * Converts an XML representation to an entity.
     *
     * @param xml the XML representation
     * @return the entity
     */
    T fromXml(X xml);

    /**
     * Converts a list of entities to their XML representation.
     *
     * @param entities the list of entities
     * @return the XML representation of the entities
     */
    List<X> toXmlList(List<T> entities);

    /**
     * Converts a list of XML representations to entities.
     *
     * @param xmlList the list of XML representations
     * @return the list of entities
     */
    List<T> fromXmlList(List<X> xmlList);

    /**
     * Serializes an entity to XML and writes it to an output stream.
     *
     * @param entity the entity to serialize
     * @param outputStream the output stream to write to
     */
    void serializeToXml(T entity, OutputStream outputStream);

    /**
     * Deserializes an entity from XML from an input stream.
     *
     * @param inputStream the input stream to read from
     * @return the deserialized entity
     */
    T deserializeFromXml(InputStream inputStream);
} 