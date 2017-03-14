package com.ugms.backend.bean.exception;

/**
 * 业务逻辑异常
 * Created by Roy on 2017/3/8.
 */
public class UserException extends RuntimeException {


    private static final long serialVersionUID = -3670599975935155854L;
    private UgmsStatus status;
    private String message = null;

    public UserException(UgmsStatus status) {
        this.status = status;
    }

    public UserException(UgmsStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getErrorNo() {
        return status.getErrorNo();
    }

    public String getError() {
        if (message != null) {
            return status.getError() + ": " + message;
        } else {
            return status.getError();
        }
    }
}
