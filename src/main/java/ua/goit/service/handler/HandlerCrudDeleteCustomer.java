package ua.goit.service.handler;

import lombok.AccessLevel;
import lombok.Getter;
import ua.goit.controller.Controller;
import ua.goit.controller.MessageSender;
import ua.goit.models.Customer;
import ua.goit.service.CustomerService;

import java.util.Optional;

public class HandlerCrudDeleteCustomer extends CommandHandler{
    @Getter(AccessLevel.PROTECTED)
    private final String[] processedCommands = {"crud", "delete", "Customer"};

    protected HandlerCrudDeleteCustomer(MessageSender messageSender, Controller controller, CommandExecutor executor) {
        super(messageSender, controller, executor);
    }

    @Override
    protected void apply(String... command) {
        Optional<Customer> customer = new CustomerService().readById(Customer.class, Long.valueOf(command[3]));
        if (customer.isPresent()){
            System.out.println("Deleting entity: " + customer.get());
            new CustomerService().deleteById(Customer.class, Long.valueOf(command[3]));
        } else {
            System.out.println("No such entity((");
        }
    }

    @Override
    protected String commandDescription() {
        return "Delete Customer by id";
    }

    @Override
    protected String commandExample() {
        return "~crud|delete|Customer|CustomerId~\n" +
                "crud|delete|Customer|4\n";
    }

    @Override
    protected Integer commandPosition() {
        return 85;
    }

    @Override
    protected int getNumberCommands() {
        return 4;
    }
}
