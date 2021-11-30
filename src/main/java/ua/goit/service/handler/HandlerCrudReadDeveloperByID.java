package ua.goit.service.handler;

import lombok.AccessLevel;
import lombok.Getter;
import ua.goit.controller.Controller;
import ua.goit.controller.MessageSender;
import ua.goit.models.Developer;
import ua.goit.service.DeveloperService;

import java.util.Optional;

public class HandlerCrudReadDeveloperByID extends CommandHandler{
    @Getter(AccessLevel.PROTECTED)
    private final String[] processedCommands = {"crud", "read", "Developer"};

    protected HandlerCrudReadDeveloperByID(MessageSender messageSender, Controller controller, CommandExecutor executor) {
        super(messageSender, controller, executor);
    }

    @Override
    protected void apply(String... command) {
        Optional<Developer> developer = new DeveloperService().readById(Developer.class, Long.valueOf(command[3]));
        messageSender.send(developer.isPresent() ? developer.get()  : "No such entity((");
    }

    @Override
    protected String commandDescription() {
        return "Read Developer by id";
    }

    @Override
    protected String commandExample() {
        return "~crud|read|Developer|DeveloperId~\n" +
                "crud|read|Developer|2\n";
    }

    @Override
    protected Integer commandPosition() {
        return 65;
    }

    @Override
    protected int getNumberCommands() {
        return 4;
    }
}
