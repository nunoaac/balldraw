package com.nunoaac.balldraw_core.balldraw.logic.algorithm;

/**
 * This exception class will be thrown whenever a ball draw fails to be generated, caused by invalid settings.
 * @author nunocosta
 */
public class InvalidDrawParametersException extends Exception {


    public InvalidDrawParametersException (String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDrawParametersException(String msg) {
        super(msg);
    }
}
