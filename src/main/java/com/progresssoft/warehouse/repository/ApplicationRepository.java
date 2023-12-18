package com.progresssoft.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * A generic repository interface that extends JpaRepository and JpaSpecificationExecutor.
 * This interface is intended to be used as a base repository for other repositories in the application.
 *
 * @param <T>  The type of the entity managed by the repository.
 * @param <ID> The type of the entity's identifier.
 */
@NoRepositoryBean
public interface ApplicationRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    // No additional methods are declared in this interface.
    // It relies on the methods provided by JpaRepository and JpaSpecificationExecutor.
}
