package ua.goit.service.handler;

import lombok.AccessLevel;
import lombok.Getter;
import ua.goit.controller.Controller;
import ua.goit.controller.MessageSender;
import ua.goit.models.Company;
import ua.goit.service.CompanyService;

public class HandlerCrudCreateCompany extends CommandHandler{

    @Getter(AccessLevel.PROTECTED)
    private final String[] processedCommands = {"crud", "create", "Company"};

    protected HandlerCrudCreateCompany(MessageSender messageSender, Controller controller, CommandExecutor executor) {
        super(messageSender, controller, executor);
    }

    @Override
    protected void apply(String... command) {
        messageSender.send("Enter parametr's for new Company: [id|name|quantity_staff]");
        Company entityForCreate = new Company(controller.read());
        new CompanyService().createEntity(Company.class, entityForCreate);
        messageSender.send("Created new entity: " + entityForCreate);
    }

    @Override
    protected String commandDescription() {
        return "Create new Company";
    }

    @Override
    protected String commandExample() {
        return "crud|create|Company\n" +
                "~id|name|quantity_staff~\n" +
                "5|CompanyX|10\n" +
                "6|CompanyY|100\n" +
                "7|CompanyZ|1000\n";
    }

    @Override
    protected Integer commandPosition() {
        return 5;
    }
}
