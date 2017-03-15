package com.ug369.backend.bean.exception;

/**
 * Created by roy on 2017/3/8.
 */
public class WebapiException extends RuntimeException {

    private static final long serialVersionUID = -264915598161887989L;
    String errorMessage;

    public WebapiException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String toString() {
        return errorMessage;
    }

    public String getMessage() {
        return errorMessage;
    }
}
