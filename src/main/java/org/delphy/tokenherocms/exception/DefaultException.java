package org.delphy.tokenherocms.exception;

import org.delphy.tokenherocms.common.EnumError;

public class DefaultException extends RuntimeException {
    private EnumError errorCode;

    public DefaultException(EnumError errorCode) {
        this.errorCode = errorCode;
    }

    public EnumError getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(EnumError errorCode) {
        this.errorCode = errorCode;
    }
}
