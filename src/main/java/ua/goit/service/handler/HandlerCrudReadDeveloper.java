package ua.goit.service.handler;

import lombok.AccessLevel;
import lombok.Getter;
import ua.goit.controller.Controller;
import ua.goit.controller.MessageSender;
import ua.goit.models.Developer;
import ua.goit.service.DeveloperService;

public class HandlerCrudReadDeveloper extends CommandHandler{
    @Getter(AccessLevel.PROTECTED)
    private final String[] processedCommands = {"crud", "read", "Developer"};

    public HandlerCrudReadDeveloper(MessageSender messageSender, Controller controller) {
        super(messageSender, controller);
    }

    @Override
    protected void apply(String... command) {
        messageSender.send(new DeveloperService().findAll(Developer.class));
    }

    @Override
    protected String commandDescription() {
        return "Read all Developers";
    }

    @Override
    protected String commandExample() {
        return "crud|read|Developer\n";
    }

    @Override
    protected Integer commandPosition() {
        return 40;
    }
}
