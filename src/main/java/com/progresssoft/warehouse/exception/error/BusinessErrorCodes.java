package com.progresssoft.warehouse.exception.error;

import java.util.stream.Stream;

/**
 * Business Error Codes used to handle exception codes and messages.
 *
 * @author Bader alnajjar
 * @version 1.0
 */
public enum BusinessErrorCodes {

    //General
    NO_DEAL_DETAILS_FOUND("001", "No deal details for this id found"),
    ;
    private String code;
    private String errorMessage;
    private Object[] args;

    /**
     * constructor.
     *
     * @param code         error code
     * @param errorMessage code message
     */
    BusinessErrorCodes(final String code, final String errorMessage, final Object... args) {
        this.code = code;
        this.errorMessage = errorMessage;
        this.args = args;
    }

    public String getCode() {
        return code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * stream business error code.
     *
     * @return business error codes
     */
    public static Stream<BusinessErrorCodes> stream() {
        return Stream.of(BusinessErrorCodes.values());
    }
}
