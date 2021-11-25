package ua.goit.service.handler;

import lombok.AccessLevel;
import lombok.Getter;
import ua.goit.controller.Controller;
import ua.goit.controller.MessageSender;
import ua.goit.models.Developer;
import ua.goit.service.DeveloperService;

import java.util.Optional;

public class HandlerCrudDeleteDeveloper extends CommandHandler{
    @Getter(AccessLevel.PROTECTED)
    private final String[] processedCommands = {"crud", "delete", "Developer"};

    public HandlerCrudDeleteDeveloper(MessageSender messageSender, Controller controller) {
        super(messageSender, controller);
    }

    @Override
    protected void apply(String... command) {
        Optional<Developer> developer = new DeveloperService().readById(Developer.class, Long.valueOf(command[3]));
        if (developer.isPresent()){
            System.out.println("Deleting entity: " + developer.get());
            new DeveloperService().deleteById(Developer.class, Long.valueOf(command[3]));
        } else {
            System.out.println("No such entity((");
        }
    }

    @Override
    protected String commandDescription() {
        return "Delete Developer by id";
    }

    @Override
    protected String commandExample() {
        return "~crud|delete|Developer|DeveloperId~\n" +
                "crud|delete|Developer|5\n";
    }

    @Override
    protected Integer commandPosition() {
        return 90;
    }

    @Override
    protected int getNumberCommands() {
        return 4;
    }
}
