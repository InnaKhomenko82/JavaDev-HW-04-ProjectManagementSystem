package ua.goit.service.handler;

import lombok.AccessLevel;
import lombok.Getter;
import ua.goit.controller.Controller;
import ua.goit.controller.MessageSender;
import ua.goit.models.Company;
import ua.goit.service.CompanyService;

import java.util.Optional;

public class HandlerCrudUpdateCompany extends CommandHandler{

    @Getter(AccessLevel.PROTECTED)
    private final String[] processedCommands = {"crud", "update", "Company"};

    protected HandlerCrudUpdateCompany(MessageSender messageSender, Controller controller, CommandExecutor executor) {
        super(messageSender, controller, executor);
    }

    @Override
    protected void apply(String... command) {
        messageSender.send("Enter parametr's for update Company: [id|name|quantity_staff]");
        Company entityForUpdate = new Company(controller.read());
        Optional<Company> company = new CompanyService().readById(Company.class, Long.valueOf(command[3]));
        if (company.isPresent()){
            messageSender.send("Updating entity: " + company.get() + " on " + entityForUpdate);
            new CompanyService().updateEntity(Company.class, entityForUpdate);
        } else {
            messageSender.send("No such entity((");
        }
    }

    @Override
    protected String commandDescription() {
        return "Update Company with id";
    }

    @Override
    protected String commandExample() {
        return "crud|update|Company|5\n";
    }

    @Override
    protected Integer commandPosition() {
        return 110;
    }

    @Override
    protected int getNumberCommands() {
        return 4;
    }
}
