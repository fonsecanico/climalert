package ar.edu.utn.ba.ddsi.climalert.services.notifications.adapters.interfaces;

import java.util.List;

public interface IEmailSender
{
    void send(List<String> addresses, String subject, String body);
}
