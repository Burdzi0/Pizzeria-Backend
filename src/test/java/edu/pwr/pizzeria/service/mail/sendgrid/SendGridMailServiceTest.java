package edu.pwr.pizzeria.service.mail.sendgrid;

import edu.pwr.pizzeria.StandardTest;
import edu.pwr.pizzeria.service.mail.BasicMail;
import edu.pwr.pizzeria.service.mail.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

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