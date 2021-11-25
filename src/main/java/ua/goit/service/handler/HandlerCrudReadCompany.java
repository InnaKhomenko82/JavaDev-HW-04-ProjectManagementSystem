package ua.goit.service.handler;

import lombok.AccessLevel;
import lombok.Getter;
import ua.goit.controller.Controller;
import ua.goit.controller.MessageSender;
import ua.goit.models.Company;
import ua.goit.service.CompanyService;

public class HandlerCrudReadCompany extends CommandHandler{

    @Getter(AccessLevel.PROTECTED)
    private final String[] processedCommands = {"crud", "read", "Company"};

    public HandlerCrudReadCompany(MessageSender messageSender, Controller controller) {
        super(messageSender, controller);
    }

    @Override
    protected void apply(String... command) {
        messageSender.send(new CompanyService().findAll(Company.class));
    }

    @Override
    protected String commandDescription() {
        return "Read all Companies";
    }

    @Override
    protected String commandExample() {
        return "crud|read|Company\n";
    }

    @Override
    protected Integer commandPosition() {
        return 30;
    }
}
