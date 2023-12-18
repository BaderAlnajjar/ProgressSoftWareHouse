package com.progresssoft.warehouse.controller;

import com.progresssoft.warehouse.business.DealDetailsBusiness;
import com.progresssoft.warehouse.exception.ProgressoftException;
import com.progresssoft.warehouse.repository.entity.DealDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * REST controller for managing DealDetails entities.
 */
@RestController
public class DealDetailsResource extends AbstractResource<DealDetails> {

    /**
     * Constructor to set the business service for DealDetails.
     *
     * @param business DealDetails business service.
     */
    public DealDetailsResource(DealDetailsBusiness business) {
        this.business = business;
    }

    /**
     * Retrieve a list of DealDetails entities based on provided filters.
     *
     * @param filters Map containing filters for the query.
     * @return Map containing the result of the query.
     */
    @GetMapping("/deal-details")
    @Override
    public Map<String, Object> getDocuments(@RequestParam Map<String, String> filters) {
        return super.getDocuments(filters);
    }

    /**
     * Retrieve a single DealDetails entity by its ID.
     *
     * @param documentId ID of the DealDetails entity to retrieve.
     * @return The retrieved DealDetails entity.
     * @throws ProgressoftException If an error occurs during the retrieval process.
     */
    @GetMapping("/deal-details/{deal_details_id}")
    @Override
    public DealDetails getDocument(@PathVariable("deal_details_id") Integer documentId) throws ProgressoftException {
        return super.getDocument(documentId);
    }

    /**
     * Add a new DealDetails entity.
     *
     * @param document The DealDetails entity to add.
     * @return The added DealDetails entity.
     * @throws ProgressoftException If an error occurs during the addition process.
     */
    @PostMapping("/deal-details")
    @Override
    public DealDetails addDocument(@RequestBody DealDetails document) throws ProgressoftException {
        return super.addDocument(document);
    }

    /**
     * Update an existing DealDetails entity.
     *
     * @param documentId ID of the DealDetails entity to update.
     * @param document   The updated DealDetails entity.
     * @throws ProgressoftException If an error occurs during the update process.
     */
    @PutMapping("/deal-details/{deal_details_id}")
    @Override
    public void updateDocument(@PathVariable("deal_details_id") Integer documentId,
                               @RequestBody DealDetails document) throws ProgressoftException {
        super.updateDocument(documentId, document);
    }

    /**
     * Delete a DealDetails entity by its ID.
     *
     * @param documentId ID of the DealDetails entity to delete.
     * @throws ProgressoftException If an error occurs during the deletion process.
     */
    @DeleteMapping("/deal-details/{deal_details_id}")
    @Override
    public void deleteDocument(@PathVariable("deal_details_id") Integer documentId) throws ProgressoftException {
        super.deleteDocument(documentId);
    }
}