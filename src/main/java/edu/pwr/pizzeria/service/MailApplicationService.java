package edu.pwr.pizzeria.service;

import edu.pwr.pizzeria.mail.BasicMail;
import edu.pwr.pizzeria.mail.MailContentFactory;
import edu.pwr.pizzeria.mail.MailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MailApplicationService {

    private MailContentFactory mailContentFactory;
    private MailService mailService;

    @Value("${app.mail.from}")
    private String from;

    public MailApplicationService(MailContentFactory mailContentFactory, MailService mailService) {
        this.mailContentFactory = mailContentFactory;
        this.mailService = mailService;
    }

    public void sendPasswordResettingMail(String mail) {
        final BasicMail basicMail = new BasicMail(from,
                mail,
                "Pizzeria ZPI - Przywrócenie hasła",
                mailContentFactory.resetPasswordMail("http://burdzi0.best"));

        mailService.sendMail(basicMail);
        sendConfirmOrderMail(mail);
        sendConfirmRegisterMail(mail);
    }

    public void sendConfirmRegisterMail(String mail) {
        final BasicMail basicMail = new BasicMail(from,
                mail,
                "Pizzeria ZPI - Rejestracja",
                mailContentFactory.registrationMail("https://www.youtube.com/watch?v=rEq1Z0bjdwc"));

        mailService.sendMail(basicMail);
    }

    public void sendConfirmOrderMail(String mail) {
        final BasicMail basicMail = new BasicMail(from,
                mail,
                "Pizzeria ZPI - Przywrócenie hasła",
                mailContentFactory.orderMail());

        mailService.sendMail(basicMail);
    }
}
