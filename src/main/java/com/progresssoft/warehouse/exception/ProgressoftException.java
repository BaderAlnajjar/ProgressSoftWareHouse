package com.progresssoft.warehouse.exception;


import com.progresssoft.warehouse.exception.error.BusinessErrorCodes;
import com.progresssoft.warehouse.exception.error.ErrorCategories;

/**
 * General exception.
 *
 * @author Bader alnajjar
 * @version 1.0
 */
public class ProgressoftException extends ApiException {

    private BusinessErrorCodes businessErrorCode;
    private Object[] args;

    /**
     * constructor.
     *
     * @param businessErrorCode Business role exception details
     */
    public ProgressoftException(final ErrorCategories errorCategories, final BusinessErrorCodes businessErrorCode) {
        this(errorCategories, businessErrorCode, null);
    }

    /**
     * constructor.
     *
     * @param businessErrorCode Business role exception details
     * @param args              message placeholder arguments
     */
    public ProgressoftException(final ErrorCategories errorCategories, final BusinessErrorCodes businessErrorCode,
                                final Object... args) {
        super(errorCategories);
        this.businessErrorCode = businessErrorCode;
        this.args = args;
    }

    public BusinessErrorCodes getBusinessErrorCode() {
        return businessErrorCode;
    }

    public Object[] getArgs() {
        return args;
    }
}
