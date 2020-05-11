package edu.pwr.pizzeria.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
public class MailContentFactory {

    @Value("${app.mail.images.title}")
    private String titleImage;

    @Value("${app.mail.images.pizzaRight}")
    private String pizzaLeft;

    @Value("${app.mail.images.pizzaLeft}")
    private String pizzaRight;

    private SpringTemplateEngine templateEngine;

    public MailContentFactory(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String registrationMail(String registerLink) {
        final Context context = new Context();
        context.setVariable("link", registerLink);
        addImages(context);
        return templateEngine.process("confirm_register", context);
    }

    public String resetPasswordMail(String link) {
        final Context context = new Context();
        context.setVariable("link", link);
        addImages(context);
        return templateEngine.process("reset_email", context);
    }

    public String orderMail() {
        final Context context = new Context();
        addImages(context);
        return templateEngine.process("confirm_order", context);
    }

    private void addImages(Context context) {
        context.setVariable("titleImg", titleImage);
        context.setVariable("pizza_right", pizzaRight);
        context.setVariable("pizza_left", pizzaLeft);
    }
}
