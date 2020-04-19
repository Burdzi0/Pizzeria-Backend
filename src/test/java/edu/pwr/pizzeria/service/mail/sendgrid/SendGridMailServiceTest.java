package edu.pwr.pizzeria.service.mail.sendgrid;

import edu.pwr.pizzeria.StandardTest;
import edu.pwr.pizzeria.service.mail.BasicMail;
import edu.pwr.pizzeria.service.mail.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class SendGridMailServiceTest extends StandardTest {

    @Autowired
    MailService mailService;

    @Test
    void sendMail() {
        String content = "<html><body><h1>Content</h1><p>Some content</p></body></html>";
        BasicMail mail = new BasicMail("242399@student.pwr.edu.pl", "242399@student.pwr.edu.pl", "Test subject", content);
        assertTrue(mailService.sendMail(mail));
    }
}