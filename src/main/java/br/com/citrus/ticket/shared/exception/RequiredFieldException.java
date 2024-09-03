package br.com.citrus.ticket.shared.exception;

public class RequiredFieldException extends RuntimeException{
    public RequiredFieldException(String message) {
        super(message);
    }
}
