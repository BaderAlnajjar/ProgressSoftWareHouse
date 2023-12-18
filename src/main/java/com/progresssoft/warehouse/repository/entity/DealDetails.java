package com.progresssoft.warehouse.repository.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Entity class representing details of a deal in the system.
 */
@Entity
@Table(name = "deal_details")
public class DealDetails extends AbstractEntity {

    private String fromCurrencyIsoCode;
    private String toCurrencyIsoCode;
    private Date dealTime;
    private BigDecimal dealAmount;

    /**
     * Get the ISO code of the currency from which the deal originates.
     *
     * @return The ISO code of the from currency.
     */
    @Column(name = "from_currency_iso_code", length = 3, nullable = false)
    public String getFromCurrencyIsoCode() {
        return fromCurrencyIsoCode;
    }

    /**
     * Set the ISO code of the currency from which the deal originates.
     *
     * @param fromCurrencyIsoCode The ISO code of the from currency to set.
     */
    public void setFromCurrencyIsoCode(String fromCurrencyIsoCode) {
        this.fromCurrencyIsoCode = fromCurrencyIsoCode;
    }

    /**
     * Get the ISO code of the currency to which the deal is made.
     *
     * @return The ISO code of the to currency.
     */
    @Column(name = "to_currency_iso_code", length = 3, nullable = false)
    public String getToCurrencyIsoCode() {
        return toCurrencyIsoCode;
    }

    /**
     * Set the ISO code of the currency to which the deal is made.
     *
     * @param toCurrencyIsoCode The ISO code of the to currency to set.
     */
    public void setToCurrencyIsoCode(String toCurrencyIsoCode) {
        this.toCurrencyIsoCode = toCurrencyIsoCode;
    }

    /**
     * Get the timestamp representing the time of the deal.
     *
     * @return The deal time.
     */
    @JsonFormat(pattern = "dd-MM-yyyy HH:MM:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deal_time", nullable = false)
    public Date getDealTime() {
        return dealTime;
    }

    /**
     * Set the timestamp representing the time of the deal.
     *
     * @param dealTime The deal time to set.
     */
    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    /**
     * Get the amount involved in the deal.
     *
     * @return The deal amount.
     */
    @Column(name = "deal_amount", nullable = false, precision = 9, scale = 3)
    public BigDecimal getDealAmount() {
        return dealAmount;
    }

    /**
     * Set the amount involved in the deal.
     *
     * @param dealAmount The deal amount to set.
     */
    public void setDealAmount(BigDecimal dealAmount) {
        this.dealAmount = dealAmount;
    }
}
