package ua.goit.service.handler;

import lombok.AccessLevel;
import lombok.Getter;
import ua.goit.controller.Controller;
import ua.goit.controller.MessageSender;
import ua.goit.models.Developer;
import ua.goit.service.DeveloperService;

public class HandlerCrudCreateDeveloper extends CommandHandler{
    @Getter(AccessLevel.PROTECTED)
    private final String[] processedCommands = {"crud", "create", "Developer"};

    protected HandlerCrudCreateDeveloper(MessageSender messageSender, Controller controller, CommandExecutor executor) {
        super(messageSender, controller, executor);
    }

    @Override
    protected void apply(String... command) {
        messageSender.send("Enter parametr's for new Developer: [id|name|age|salary]");
        Developer entityForCreate = new Developer(controller.read());
        new DeveloperService().createEntity(Developer.class, entityForCreate);
        messageSender.send("Created new entity: " + entityForCreate);
    }

    @Override
    protected String commandDescription() {
        return "Create new Developer";
    }

    @Override
    protected String commandExample() {
        return "crud|create|Developer\n" +
                "~id|name|age|salary~\n" +
                "5|DeveloperX|48|600\n";
    }

    @Override
    protected Integer commandPosition() {
        return 15;
    }
}
