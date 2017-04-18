package com.ug369.backend.bean.exception;

/**
 * 业务异常.
 */
public class BusinessException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5012486043698868749L;

	private int errorCode;

    public BusinessException() { super(); }

    public BusinessException(int errorCode) {
        this.errorCode = errorCode;
    }

    public BusinessException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessException(String message, Throwable cause, int errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public BusinessException(Throwable cause, int errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message) {
        super(message);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public BusinessException setErrorCode(int errorCode) {
        this.errorCode = errorCode;
        return this;
    }

}
