package edu.pwr.pizzeria.controller;

import edu.pwr.pizzeria.mail.BasicMail;
import edu.pwr.pizzeria.mail.MailContentFactory;
import edu.pwr.pizzeria.mail.MailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

@RestController
@RequestMapping("/v1/mail")
public class MailController {

    private MailService mailService;
    private MailContentFactory factory;

    public MailController(MailService mailService, MailContentFactory factory) {
        this.mailService = mailService;
        this.factory = factory;
    }

    @PermitAll
    @GetMapping
    public void sendMail() {
        final BasicMail basicMail = new BasicMail("wroclavbestpizza@gmail.com",
                "242399@student.pwr.edu.pl",
                "Pizzeria ZPI",
                factory.registrationMail());
        System.out.println("Mail: " + mailService.sendMail(basicMail));
    }

    @PermitAll
    @GetMapping("/test")
    public String showMail() {
        final BasicMail basicMail = new BasicMail("wroclavbestpizza@gmail.com",
                "242399@student.pwr.edu.pl",
                "Pizzeria ZPI",
                factory.registrationMail());

        return basicMail.getContent();
    }

}
