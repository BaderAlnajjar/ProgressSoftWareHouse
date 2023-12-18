package com.progresssoft.warehouse.repository.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

/**
 * An abstract class representing the common attributes for all entities in the system.
 */
@MappedSuperclass
public abstract class AbstractEntity {

    private Integer id;
    private Date createdTime;
    private Date lastModifiedTime;

    /**
     * Get the unique identifier for the entity.
     *
     * @return The ID of the entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    /**
     * Set the unique identifier for the entity.
     *
     * @param id The ID to set.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Get the timestamp representing the creation time of the entity.
     *
     * @return The creation time of the entity.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_time", nullable = false, updatable = false)
    @CreatedDate
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * Set the timestamp representing the creation time of the entity.
     *
     * @param createdTime The creation time to set.
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * Get the timestamp representing the last modification time of the entity.
     *
     * @return The last modification time of the entity.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modified_time", nullable = false)
    @LastModifiedDate
    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    /**
     * Set the timestamp representing the last modification time of the entity.
     *
     * @param lastModifiedTime The last modification time to set.
     */
    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    /**
     * Generate a hash code for the entity based on its ID.
     *
     * @return The hash code of the entity.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    /**
     * Check if this entity is equal to another object based on their IDs.
     *
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractEntity other = (AbstractEntity) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        return true;
    }
}

