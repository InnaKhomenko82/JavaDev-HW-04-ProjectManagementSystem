package ua.goit.service.handler;

import lombok.AccessLevel;
import lombok.Getter;
import ua.goit.controller.Controller;
import ua.goit.controller.MessageSender;
import ua.goit.models.Project;
import ua.goit.service.ProjectService;

public class HandlerCrudReadProject extends CommandHandler{
    @Getter(AccessLevel.PROTECTED)
    private final String[] processedCommands = {"crud", "read", "Project"};

    public HandlerCrudReadProject(MessageSender messageSender, Controller controller) {
        super(messageSender, controller);
    }

    @Override
    protected void apply(String... command) {
        messageSender.send(new ProjectService().findAll(Project.class));
    }

    @Override
    protected String commandDescription() {
        return "Read all Projects";
    }

    @Override
    protected String commandExample() {
        return "crud|read|Project\n";
    }

    @Override
    protected Integer commandPosition() {
        return 45;
    }
}
