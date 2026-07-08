package ar.edu.utn.ba.ddsi.climalert.exceptions;

public class SendGridResponseException extends RuntimeException {
    public SendGridResponseException(String message) {
        super(message);
    }
}
