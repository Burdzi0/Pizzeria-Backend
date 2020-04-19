package edu.pwr.pizzeria.service.mail.sendgrid;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import edu.pwr.pizzeria.service.mail.BasicMail;
import edu.pwr.pizzeria.service.mail.MailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
public class SendGridMailService implements MailService {

    public static final String TEXT_HTML = "text/html";
    private final SendGrid sendGrid;

    public SendGridMailService(SendGrid sendGrid) {
        this.sendGrid = sendGrid;
    }

    @Override
    @Transactional
    public boolean sendMail(BasicMail basicMail) {
        final var mail = createSendGridMail(basicMail);

        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        try {
            request.setBody(mail.build());
            Response response = this.sendGrid.api(request);
            return response.getStatusCode() == 202;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Mail createSendGridMail(BasicMail basicMail) {
        final var from = new Email(basicMail.getFrom());
        final var to = new Email(basicMail.getTo());
        final Content content = new Content(TEXT_HTML, basicMail.getContent());

        return new Mail(from, basicMail.getSubject(), to, content);
    }
}
