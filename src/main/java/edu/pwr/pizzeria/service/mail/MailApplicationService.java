package edu.pwr.pizzeria.service.mail;

import edu.pwr.pizzeria.mail.BasicMail;
import edu.pwr.pizzeria.mail.MailContentFactory;
import edu.pwr.pizzeria.mail.MailService;
import edu.pwr.pizzeria.model.order.CustomerOrder;
import edu.pwr.pizzeria.model.order.dto.CustomerOrderDto;
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
    }

    public void sendConfirmRegisterMail(String mail, String registerLink) {
        final BasicMail basicMail = new BasicMail(from,
                mail,
                "Pizzeria ZPI - Rejestracja",
                mailContentFactory.registrationMail(registerLink));

        mailService.sendMail(basicMail);
    }

    public void sendConfirmOrderMail(String mail, CustomerOrder customerOrder) {
        final BasicMail basicMail = new BasicMail(from,
                mail,
                "Pizzeria ZPI - Potwierdzenie zamówienia",
                mailContentFactory.orderMail(customerOrder.getId(), customerOrder.getPizzas(), customerOrder.getCustoms()));
        mailService.sendMail(basicMail);
    }
}
