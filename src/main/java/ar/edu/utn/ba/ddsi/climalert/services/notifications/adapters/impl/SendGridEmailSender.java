package ar.edu.utn.ba.ddsi.climalert.services.notifications.adapters.impl;

import ar.edu.utn.ba.ddsi.climalert.services.notifications.adapters.interfaces.IEmailSender;
import ar.edu.utn.ba.ddsi.climalert.services.notifications.connectors.SendGridConnector;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class SendGridEmailSender implements IEmailSender
{
    @Value("${sendgrid.sender}")
    private String senderEmail;
    private final SendGridConnector connector;

    @Override
    public void send(List<String> addresses, String subject, String body)
    {
        addresses.forEach(address -> connector.send(senderEmail, subject, body, address));
    }
}
