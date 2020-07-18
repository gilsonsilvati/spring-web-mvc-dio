package br.com.globallabs.exception;

public class JediNotFoundException extends RuntimeException {

    public JediNotFoundException() { }

    public JediNotFoundException(String message) {
        super(message);
    }

    public JediNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
