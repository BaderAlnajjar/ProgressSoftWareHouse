package com.progresssoft.warehouse.business;

import com.progresssoft.warehouse.exception.ProgressoftException;
import com.progresssoft.warehouse.exception.error.BusinessErrorCodes;
import com.progresssoft.warehouse.exception.error.ErrorCategories;
import com.progresssoft.warehouse.repository.DealDetailsRepository;
import com.progresssoft.warehouse.repository.entity.DealDetails;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

/**
 * Business logic class for dealing with {@link DealDetails} entities.
 */
@Service
public class DealDetailsBusiness extends AbstractBusiness<DealDetails> {

    /**
     * Constructor for DealDetailsBusiness.
     *
     * @param repository The repository for DealDetails entities.
     */
    public DealDetailsBusiness(DealDetailsRepository repository) {
        super.repository = repository;
    }

    /**
     * Retrieves the conditions predicates based on the provided filters for DealDetails entities.
     *
     * @param root            The root of the query, corresponding to the DealDetails entity.
     * @param criteriaBuilder The criteria builder to construct query predicates.
     * @param filters         The filters to apply during the search.
     * @return A list of predicates representing the conditions based on the provided filters.
     */
    @Override
    protected List<Predicate> getConditionsPredicates(Root<DealDetails> root, CriteriaBuilder criteriaBuilder,
                                                      Map<String, String> filters) {
        List<Predicate> conditions = new ArrayList<>();
        for (Entry<String, String> filterEntry : filters.entrySet()) {
            if (filterEntry.getValue() == null || filterEntry.getValue().isEmpty()) {
                continue;
            }
            if (filterEntry.getKey().equals("id")) {
                conditions.add(criteriaBuilder.like(root.get("id").as(String.class),
                        "%" + filterEntry.getValue().toLowerCase() + "%"));
            } else {
                conditions.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(filterEntry.getKey())),
                        "%" + filterEntry.getValue().toLowerCase() + "%"));

            }
        }
        return conditions;
    }

    /**
     * Retrieves a DealDetails document by its unique identifier.
     *
     * @param id The unique identifier of the DealDetails document.
     * @return The DealDetails document corresponding to the provided identifier.
     * @throws ProgressoftException If no DealDetails document is found for the provided identifier.
     */
    @Override
    public DealDetails getDocument(Integer id) throws ProgressoftException {

        Optional<DealDetails> dealDetails = this.repository.findById(id);

        if (dealDetails.isEmpty()) {
            throw new ProgressoftException(ErrorCategories.BUSINESS_ROLE_ERROR,
                    BusinessErrorCodes.NO_DEAL_DETAILS_FOUND);
        }
        return dealDetails.get();
    }
}