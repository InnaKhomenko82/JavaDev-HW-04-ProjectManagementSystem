package ua.goit.service.handler;

import lombok.AccessLevel;
import lombok.Getter;
import ua.goit.controller.Controller;
import ua.goit.controller.MessageSender;
import ua.goit.models.Project;
import ua.goit.service.ProjectService;

public class HandlerCrudCreateProject extends CommandHandler{
    @Getter(AccessLevel.PROTECTED)
    private final String[] processedCommands = {"crud", "create", "Project"};

    public HandlerCrudCreateProject(MessageSender messageSender, Controller controller) {
        super(messageSender, controller);
    }

    @Override
    protected void apply(String... command) {
        messageSender.send("Enter parametr's for new Project: [id|name|start|cost]");
        Project entityForCreate = new Project(controller.read());
        new ProjectService().createEntity(Project.class, entityForCreate);
        messageSender.send("Created new entity: " + entityForCreate);
    }

    @Override
    protected String commandDescription() {
        return "Create new Project";
    }

    @Override
    protected String commandExample() {
        return "crud|create|Project\n" +
                "~id|name|start|cost~\n" +
                "5|ProjectX|20.10.2021|5000\n";
    }

    @Override
    protected Integer commandPosition() {
        return 20;
    }
}
