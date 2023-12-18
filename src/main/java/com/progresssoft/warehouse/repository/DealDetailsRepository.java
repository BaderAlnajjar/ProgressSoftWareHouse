package com.progresssoft.warehouse.repository;

import com.progresssoft.warehouse.repository.entity.DealDetails;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing and managing DealDetails entities in the database.
 * Extends the ApplicationRepository interface, providing CRUD and specification query functionality.
 *
 * @see ApplicationRepository
 * @see DealDetails
 */
@Repository
public interface DealDetailsRepository extends ApplicationRepository<DealDetails, Integer> {

    /**
     * Retrieves a DealDetails entity by the specified fromCurrencyIsoCode.
     *
     * @param fromCurrencyIsoCode The ISO code of the from currency.
     * @return The DealDetails entity with the specified fromCurrencyIsoCode, or null if not found.
     */
    DealDetails findByFromCurrencyIsoCode(String fromCurrencyIsoCode);

    // Additional custom query methods can be declared here if needed.
}

