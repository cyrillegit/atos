package com.example.atos.exceptions;

/**
 * exception management
 */
public class AtosException extends Exception {

    private static final long serialVersionUID = 4331690856005947535L;

    /***/
    public AtosException() {
        super();
    }

    /**
     * @param msg Message of the exception
     */
    public AtosException(String msg) {
        super(msg);
    }

    /**
     * @param msg message of exception
     * @param th  exception
     */
    public AtosException(String msg, Throwable th) {
        super(msg, th);
    }

    /**
     * @param th exception
     */
    public AtosException(Throwable th) {
        super(th);
    }

}
