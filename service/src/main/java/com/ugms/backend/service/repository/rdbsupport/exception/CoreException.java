package com.ugms.backend.service.repository.rdbsupport.exception;

public class CoreException extends RuntimeException {
    private static final long serialVersionUID = 510212821003131L;

    public CoreException() {
        super();
    }

    public CoreException(Throwable t) {
        super(t);
    }

    public CoreException(String msg) {
        super(msg);
    }

    public CoreException(String msg, Throwable t) {
        super(msg, t);
    }

    public Exception getOrginException() {
        return (Exception) getCause();
    }
}
