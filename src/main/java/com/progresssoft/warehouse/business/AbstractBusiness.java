package com.progresssoft.warehouse.business;

import com.progresssoft.warehouse.exception.ProgressoftException;
import com.progresssoft.warehouse.repository.ApplicationRepository;
import com.progresssoft.warehouse.repository.entity.AbstractEntity;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class AbstractBusiness<T extends AbstractEntity> {

    protected ApplicationRepository<T, Integer> repository;

    public T getDocument(Integer id) throws ProgressoftException {
        return getRepository().findById(id).orElse(null);
    }

    public final Map<String, Object> findDocuments(Map<String, String> filters) {

        int pageNumber = 0;
        int pageSize = 20;
        if (filters.containsKey("page_number") && filters.containsKey("page_size")) {
            pageNumber = Integer.parseInt(filters.remove("page_number"));
            pageSize = Integer.parseInt(filters.remove("page_size"));
        }
        final String sortField;
        if (filters.get("sort_field") == null || filters.get("sort_field").isEmpty()) {
            sortField = "lastModifiedTime";
        } else {
            sortField = filters.remove("sort_field");
        }
        final String sortDirection;
        if (filters.get("sort_order") == null || filters.get("sort_order").isEmpty()) {
            sortDirection = "desc";
        } else {
            sortDirection = filters.remove("sort_order");
        }

        Specification<T> specification = new Specification<T>() {

            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

                query.distinct(true);
                Predicate predicate = criteriaBuilder
                        .and(getConditionsPredicates(root, criteriaBuilder, filters).toArray(new Predicate[0]));
                Expression<String> sortExpression = getSortExpression(root, criteriaBuilder, sortField);
                if (sortDirection.equals("asc")) {
                    query.orderBy(criteriaBuilder.asc(sortExpression));
                } else {
                    query.orderBy(criteriaBuilder.desc(sortExpression));
                }
                return predicate;
            }
        };

        Map<String, Object> result = new LinkedHashMap<>();
        List<T> documents;
        if (pageSize == -1) {
            documents = getRepository().findAll(specification);
        } else {
            Page<T> page = getRepository().findAll(specification, PageRequest.of(pageNumber, pageSize));
            documents = new ArrayList<>();
            page.forEach(documents::add);
            result.put("totalElements", page.getTotalElements());
        }
        result.put("rows", documents);
        return result;
    }

    protected List<Predicate> getConditionsPredicates(Root<T> root, CriteriaBuilder criteriaBuilder,
                                                      Map<String, String> filters) {

        List<Predicate> conditions = new ArrayList<>();
        for (Entry<String, String> filterEntry : filters.entrySet()) {
            if (filterEntry.getValue() == null || filterEntry.getValue().isEmpty()) {
                continue;
            }
            conditions.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(filterEntry.getKey())),
                    "%" + filterEntry.getValue().toLowerCase() + "%"));
        }
        return conditions;
    }

    protected Expression<String> getSortExpression(Root<T> root, CriteriaBuilder criteriaBuilder, String sortField) {
        return root.get(sortField);
    }

    public void saveDocument(T document) throws ProgressoftException {
        getRepository().save(document);
    }

    public void deleteDocument(Integer id) throws ProgressoftException {
        getRepository().deleteById(id);
    }

    public ApplicationRepository<T, Integer> getRepository() {
        return repository;
    }

}
