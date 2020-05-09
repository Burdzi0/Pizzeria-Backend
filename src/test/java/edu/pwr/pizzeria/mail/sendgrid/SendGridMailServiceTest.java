package edu.pwr.pizzeria.mail.sendgrid;

import edu.pwr.pizzeria.StandardTest;
import edu.pwr.pizzeria.mail.BasicMail;
import edu.pwr.pizzeria.mail.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class SendGridMailServiceTest extends StandardTest {

    @Autowired
    private MailService mailService;

    @Test
    void sendTestMail() {
        BasicMail basicMail = new BasicMail("wroclavbestpizza@gmail.com ",
                "242399@student.pwr.edu.pl",
                "Pizzeria ZPI",
                "<h1>Tekst</h1>");
        mailService.sendMail(basicMail);
    }
}