package ar.edu.utn.ba.ddsi.climalert.exceptions;

public class SendGridNetworkException extends RuntimeException {
    public SendGridNetworkException(String message) {
        super(message);
    }
}
