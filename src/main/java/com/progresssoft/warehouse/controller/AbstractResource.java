package com.progresssoft.warehouse.controller;

import com.progresssoft.warehouse.business.AbstractBusiness;
import com.progresssoft.warehouse.exception.ProgressoftException;
import com.progresssoft.warehouse.repository.entity.AbstractEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Abstract resource class providing common CRUD operations for entities.
 *
 * @param <T> Type of the entity.
 */
public class AbstractResource<T extends AbstractEntity> {

    /**
     * The business service associated with the entity.
     */
    protected AbstractBusiness<T> business;

    /**
     * Retrieves a list of documents based on the provided filters.
     *
     * @param filters Map containing filters for the query.
     * @return Map containing the result of the query.
     */
    public Map<String, Object> getDocuments(@RequestParam Map<String, String> filters) {
        return business.findDocuments(filters);
    }

    /**
     * Retrieves a single document by its ID.
     *
     * @param documentId ID of the document to retrieve.
     * @return The retrieved document.
     * @throws ProgressoftException If an error occurs during the retrieval process.
     */
    public T getDocument(Integer documentId) throws ProgressoftException {
        return business.getDocument(documentId);
    }

    /**
     * Adds a new document.
     *
     * @param document The document to add.
     * @return The added document.
     * @throws ProgressoftException If an error occurs during the addition process.
     */
    public T addDocument(T document) throws ProgressoftException {
        business.saveDocument(document);
        return document;
    }

    /**
     * Updates an existing document.
     *
     * @param documentId ID of the document to update.
     * @param document   The updated document.
     * @throws ProgressoftException If an error occurs during the update process.
     */
    public void updateDocument(Integer documentId, T document) throws ProgressoftException {
        document.setId(documentId);
        business.saveDocument(document);
    }

    /**
     * Deletes a document by its ID.
     *
     * @param documentId ID of the document to delete.
     * @throws ProgressoftException If an error occurs during the deletion process.
     */
    public void deleteDocument(Integer documentId) throws ProgressoftException {
        business.deleteDocument(documentId);
    }
}