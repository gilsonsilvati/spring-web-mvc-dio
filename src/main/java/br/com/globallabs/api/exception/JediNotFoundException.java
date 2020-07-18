package br.com.globallabs.api.exception;

public class JediNotFoundException extends RuntimeException {

    public JediNotFoundException(String message) {
        super(message);
    }

    public JediNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
