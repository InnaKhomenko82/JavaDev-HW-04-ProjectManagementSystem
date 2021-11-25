package ua.goit.service.handler;

import lombok.AccessLevel;
import lombok.Getter;
import ua.goit.controller.Controller;
import ua.goit.controller.MessageSender;
import ua.goit.models.Company;
import ua.goit.service.CompanyService;

import java.util.Optional;

public class HandlerCrudDeleteCompany extends CommandHandler{

    @Getter(AccessLevel.PROTECTED)
    private final String[] processedCommands = {"crud", "delete", "Company"};

    public HandlerCrudDeleteCompany(MessageSender messageSender, Controller controller) {
        super(messageSender, controller);
    }

    @Override
    protected void apply(String... command) {
        Optional<Company> company = new CompanyService().readById(Company.class, Long.valueOf(command[3]));
        if (company.isPresent()){
            System.out.println("Deleting entity: " + company.get());
            new CompanyService().deleteById(Company.class, Long.valueOf(command[3]));
        } else {
            System.out.println("No such entity((");
        }
    }

    @Override
    protected String commandDescription() {
        return "Delete Company by id";
    }

    @Override
    protected String commandExample() {
        return "~crud|delete|Company|CompanyId~\n" +
                "crud|delete|Company|5\n";
    }

    @Override
    protected Integer commandPosition() {
        return 80;
    }

    @Override
    protected int getNumberCommands() {
        return 4;
    }
}
