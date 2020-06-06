package edu.pwr.pizzeria.mail;

import edu.pwr.pizzeria.model.order.CustomPizza;
import edu.pwr.pizzeria.model.order.OrderedPizza;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.List;
import java.util.stream.Collectors;

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

    public String orderMail(Long id, List<OrderedPizza> pizzas, List<CustomPizza> customs) {
        final Context context = new Context();
        context.setVariable("id", id);
        context.setVariable("pizzas", toFormattedString(pizzas));
        context.setVariable("customs", toFormattedStringCustoms(customs));
        addImages(context);
        return templateEngine.process("confirm_order", context);
    }

    private void addImages(Context context) {
        context.setVariable("titleImg", titleImage);
        context.setVariable("pizza_right", pizzaRight);
        context.setVariable("pizza_left", pizzaLeft);
    }

    private String toFormattedString(List<OrderedPizza> pizzas){

        if(pizzas.isEmpty()) return "";
        else return pizzas.stream().map(p -> p.toString()).collect(Collectors.toList()).toString();
    }

    private String toFormattedStringCustoms(List<CustomPizza> pizzas){

        if(pizzas.isEmpty()) return "";
        return pizzas.stream().map(p -> p.toString()).collect(Collectors.toList()).toString();
    }
}
