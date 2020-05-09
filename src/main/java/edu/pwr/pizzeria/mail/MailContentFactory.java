package edu.pwr.pizzeria.mail;

import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
public class MailContentFactory {

    private SpringTemplateEngine templateEngine;
    private ImageConverter imageConverter;

    public MailContentFactory(SpringTemplateEngine templateEngine, ImageConverter imageConverter) {
        this.templateEngine = templateEngine;
        this.imageConverter = imageConverter;
    }

    public String registrationMail() {
        final String message = "https://test-link.com";
        final Context context = new Context();
        context.setVariable("link", message);
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
        context.setVariable("titleImg", imageConverter.processImage("title.png"));
        context.setVariable("pizza_right", imageConverter.processImage("pizza_right.jpg"));
        context.setVariable("pizza_left", imageConverter.processImage("pizza_left.jpg"));
    }
}
