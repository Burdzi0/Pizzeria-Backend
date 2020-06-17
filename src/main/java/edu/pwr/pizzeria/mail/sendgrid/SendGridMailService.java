package edu.pwr.pizzeria.mail.sendgrid;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import edu.pwr.pizzeria.mail.BasicMail;
import edu.pwr.pizzeria.mail.MailSendingException;
import edu.pwr.pizzeria.mail.MailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SendGridMailService implements MailService {

    public static final String TEXT_HTML = "text/html";
    final Logger logger = LogManager.getLogger(this.getClass());
    private final SendGrid sendGrid;

    public SendGridMailService(SendGrid sendGrid) {
        this.sendGrid = sendGrid;
    }

    @Async
    @Override
    public void sendMail(BasicMail basicMail) {
        final var mail = createSendGridMail(basicMail);

        final Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");

        try {
            request.setBody(mail.build());
            this.sendGrid.api(request);
            logger.info("Mail sent successfully");
        } catch (IOException e) {
            logger.error("Mail did not send successfully", e);
            throw new MailSendingException(e);
        }
    }

    private Mail createSendGridMail(BasicMail basicMail) {
        final var from = new Email(basicMail.getFrom());
        from.setName("Najlepsza pizzeria w mieście");

        final var to = new Email(basicMail.getTo());
        final Content content = new Content(TEXT_HTML, basicMail.getContent());

        return new Mail(from, basicMail.getSubject(), to, content);
    }
}
