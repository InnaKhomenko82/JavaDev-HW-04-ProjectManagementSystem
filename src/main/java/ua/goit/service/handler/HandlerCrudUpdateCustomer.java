package ua.goit.service.handler;

import lombok.AccessLevel;
import lombok.Getter;
import ua.goit.controller.Controller;
import ua.goit.controller.MessageSender;
import ua.goit.models.Customer;
import ua.goit.service.CustomerService;

import java.util.Optional;

public class HandlerCrudUpdateCustomer extends CommandHandler{

    @Getter(AccessLevel.PROTECTED)
    private final String[] processedCommands = {"crud", "update", "Customer"};

    public HandlerCrudUpdateCustomer(MessageSender messageSender, Controller controller) {
        super(messageSender, controller);
    }

    @Override
    protected void apply(String... command) {
        messageSender.send("Enter parametr's for update Customer: [id|name|category]");
        Customer entityForUpdate = new Customer(controller.read());
        Optional<Customer> customer = new CustomerService().readById(Customer.class, Long.valueOf(command[3]));
        if (customer.isPresent()){
            messageSender.send("Updating entity: " + customer.get() + " on " + entityForUpdate);
            new CustomerService().updateEntity(Customer.class, entityForUpdate);
        } else {
            messageSender.send("No such entity((");
        }
    }

    @Override
    protected String commandDescription() {
        return "Update Customer with id";
    }

    @Override
    protected String commandExample() {
        return "crud|update|Customer|5\n";
    }

    @Override
    protected Integer commandPosition() {
        return 115;
    }

    @Override
    protected int getNumberCommands() {
        return 4;
    }
}
