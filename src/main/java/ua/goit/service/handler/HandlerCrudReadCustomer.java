package ua.goit.service.handler;

import lombok.AccessLevel;
import lombok.Getter;
import ua.goit.controller.Controller;
import ua.goit.controller.MessageSender;
import ua.goit.models.Customer;
import ua.goit.service.CustomerService;

public class HandlerCrudReadCustomer extends CommandHandler{
    @Getter(AccessLevel.PROTECTED)
    private final String[] processedCommands = {"crud", "read", "Customer"};

    protected HandlerCrudReadCustomer(MessageSender messageSender, Controller controller, CommandExecutor executor) {
        super(messageSender, controller, executor);
    }

    @Override
    protected void apply(String... command) {
        messageSender.send(new CustomerService().findAll(Customer.class));
    }

    @Override
    protected String commandDescription() {
        return "Read all Customers";
    }

    @Override
    protected String commandExample() {
        return "crud|read|Customer\n";
    }

    @Override
    protected Integer commandPosition() {
        return 35;
    }
}
