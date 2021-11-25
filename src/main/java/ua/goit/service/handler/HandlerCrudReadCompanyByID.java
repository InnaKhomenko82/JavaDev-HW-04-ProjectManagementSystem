package ua.goit.service.handler;

import lombok.AccessLevel;
import lombok.Getter;
import ua.goit.controller.Controller;
import ua.goit.controller.MessageSender;
import ua.goit.models.Company;
import ua.goit.service.CompanyService;

import java.util.Optional;

public class HandlerCrudReadCompanyByID extends CommandHandler{

    @Getter(AccessLevel.PROTECTED)
    private final String[] processedCommands = {"crud", "read", "Company"};

    public HandlerCrudReadCompanyByID(MessageSender messageSender, Controller controller) {
        super(messageSender, controller);
    }

    @Override
    protected void apply(String... command) {
        Optional<Company> company = new CompanyService().readById(Company.class, Long.valueOf(command[3]));
        messageSender.send(company.isPresent() ? company.get()  : "No such entity((");
    }

    @Override
    protected String commandDescription() {
        return "Read Company by id";
    }

    @Override
    protected String commandExample() {
        return "~crud|read|Company|CompanyId~\n" +
                "crud|read|Company|2\n";
    }

    @Override
    protected Integer commandPosition() {
        return 55;
    }

    @Override
    protected int getNumberCommands() {
        return 4;
    }
}
