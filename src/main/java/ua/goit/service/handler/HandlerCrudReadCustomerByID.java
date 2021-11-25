package ua.goit.service.handler;

import lombok.AccessLevel;
import lombok.Getter;
import ua.goit.controller.Controller;
import ua.goit.controller.MessageSender;
import ua.goit.models.Customer;
import ua.goit.service.CustomerService;

import java.util.Optional;

public class HandlerCrudReadCustomerByID extends CommandHandler{
    @Getter(AccessLevel.PROTECTED)
    private final String[] processedCommands = {"crud", "read", "Customer"};

    public HandlerCrudReadCustomerByID(MessageSender messageSender, Controller controller) {
        super(messageSender, controller);
    }

    @Override
    protected void apply(String... command) {
        Optional<Customer> customer = new CustomerService().readById(Customer.class, Long.valueOf(command[3]));
        messageSender.send(customer.isPresent() ? customer.get()  : "No such entity((");
    }

    @Override
    protected String commandDescription() {
        return "Read Customer by id";
    }

    @Override
    protected String commandExample() {
        return "~crud|read|Customer|CustomerId~\n" +
                "crud|read|Customer|2\n";
    }

    @Override
    protected Integer commandPosition() {
        return 60;
    }

    @Override
    protected int getNumberCommands() {
        return 4;
    }
}
