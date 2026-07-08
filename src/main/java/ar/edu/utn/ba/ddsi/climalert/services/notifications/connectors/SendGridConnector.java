package ar.edu.utn.ba.ddsi.climalert.services.notifications.connectors;

import ar.edu.utn.ba.ddsi.climalert.exceptions.SendGridNetworkException;
import ar.edu.utn.ba.ddsi.climalert.exceptions.SendGridResponseException;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class SendGridConnector
{
    @Value("${sendgrid.api-key}")
    private String apiKey;

    public void send(String sender, String subject, String body, String address)
    {
        Mail mailToSend = new Mail( new Email(sender),
                                    subject,
                                    new Email(address),
                                    new Content("text/plain", body));
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        try
        {
            request.setBody(mailToSend.build());
            SendGrid sendGrid = new SendGrid(this.apiKey);
            Response response = sendGrid.api(request);
            if(response.getStatusCode() >= 200 && response.getStatusCode() < 300)
            {
                log.info("Mail enviado satisfactoriamente a {}", address);
            }
            else
            {
                log.error("Rechazo en el envío de correo.\nStatusCode: {}\nBody: {}", response.getStatusCode(), response.getBody());
                throw new SendGridResponseException("Error en respuesta de SendGrid: \nStatusCode: " + response.getStatusCode() + "\nBody: " + response.getBody());
            }
        }
        catch (Exception exception)
        {
            log.error("Error de red al conectarse con SendGrid: {}", exception.getMessage(), exception);
            throw new SendGridNetworkException("Error de red al conectarse con SendGrid:\n" + exception.getMessage() + "\n" + exception);
        }
    }
}
