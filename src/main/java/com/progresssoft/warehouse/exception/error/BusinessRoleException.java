package com.progresssoft.warehouse.exception.error;


import com.progresssoft.warehouse.exception.ProgressoftException;

/**
 * Business role exception used to handle role exception.
 *
 * @author Bader alnajjar
 * @version 1.0
 */
public class BusinessRoleException extends ProgressoftException {

    /**
     * Constructor.
     *
     * @param businessErrorCode businessErrorCode
     */
    public BusinessRoleException(final BusinessErrorCodes businessErrorCode) {
        super(ErrorCategories.BUSINESS_ROLE_ERROR, businessErrorCode);
    }

    /**
     * Constructor.
     *
     * @param businessErrorCode businessErrorCode
     * @param args              args
     */
    public BusinessRoleException(final BusinessErrorCodes businessErrorCode, final Object... args) {
        super(ErrorCategories.BUSINESS_ROLE_ERROR, businessErrorCode, args);
    }
}
