package ua.goit.service.handler;

import lombok.AccessLevel;
import lombok.Getter;
import ua.goit.controller.Controller;
import ua.goit.controller.MessageSender;
import ua.goit.models.Developer;
import ua.goit.service.DeveloperService;

import java.util.Optional;

public class HandlerCrudUpdateDeveloper extends CommandHandler{

    @Getter(AccessLevel.PROTECTED)
    private final String[] processedCommands = {"crud", "update", "Developer"};

    protected HandlerCrudUpdateDeveloper(MessageSender messageSender, Controller controller, CommandExecutor executor) {
        super(messageSender, controller, executor);
    }

    @Override
    protected void apply(String... command) {
        messageSender.send("Enter parametr's for update Developer: [id|name|age|salary]");
        Developer entityForUpdate = new Developer(controller.read());
        Optional<Developer> developer = new DeveloperService().readById(Developer.class, Long.valueOf(command[3]));
        if (developer.isPresent()){
            messageSender.send("Updating entity: " + developer.get() + " on " + entityForUpdate);
            new DeveloperService().updateEntity(Developer.class, entityForUpdate);
        } else {
            messageSender.send("No such entity((");
        }
    }

    @Override
    protected String commandDescription() {
        return "Update Developer with id";
    }

    @Override
    protected String commandExample() {
        return "crud|update|Developer|5\n";
    }

    @Override
    protected Integer commandPosition() {
        return 120;
    }

    @Override
    protected int getNumberCommands() {
        return 4;
    }
}
