package ua.goit.service.handler;

import lombok.AccessLevel;
import lombok.Getter;
import ua.goit.controller.Controller;
import ua.goit.controller.MessageSender;
import ua.goit.models.Customer;
import ua.goit.service.CustomerService;

public class HandlerCrudCreateCustomer extends CommandHandler{
    @Getter(AccessLevel.PROTECTED)
    private final String[] processedCommands = {"crud", "create", "Customer"};

    protected HandlerCrudCreateCustomer(MessageSender messageSender, Controller controller) {
        super(messageSender, controller);
    }

    @Override
    protected void apply(String... command) {
        messageSender.send("Enter parametr's for new Customer: [id|name|category]");
        Customer entityForCreate = new Customer(controller.read());
        new CustomerService().createEntity(Customer.class, entityForCreate);
        messageSender.send("Created new entity: " + entityForCreate);
    }

    @Override
    protected String commandDescription() {
        return "Create new Customer";
    }

    @Override
    protected String commandExample() {
        return "crud|create|Customer\n" +
                "~id|name|category~\n" +
                "4|CustomerX|lost\n";
    }

    @Override
    protected Integer commandPosition() {
        return 10;
    }
}
