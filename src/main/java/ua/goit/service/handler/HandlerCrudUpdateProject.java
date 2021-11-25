package ua.goit.service.handler;

import lombok.AccessLevel;
import lombok.Getter;
import ua.goit.controller.Controller;
import ua.goit.controller.MessageSender;
import ua.goit.models.Project;
import ua.goit.service.ProjectService;

import java.util.Optional;

public class HandlerCrudUpdateProject extends CommandHandler{

    @Getter(AccessLevel.PROTECTED)
    private final String[] processedCommands = {"crud", "update", "Project"};

    public HandlerCrudUpdateProject(MessageSender messageSender, Controller controller) {
        super(messageSender, controller);
    }

    @Override
    protected void apply(String... command) {
        messageSender.send("Enter parametr's for update Project: [id|name|start|cost]");
        Project entityForUpdate = new Project(controller.read());
        Optional<Project> project = new ProjectService().readById(Project.class, Long.valueOf(command[3]));
        if (project.isPresent()){
            messageSender.send("Updating entity: " + project.get() + " on " + entityForUpdate);
            new ProjectService().updateEntity(Project.class, entityForUpdate);
        } else {
            messageSender.send("No such entity((");
        }
    }

    @Override
    protected String commandDescription() {
        return "Update Project with id";
    }

    @Override
    protected String commandExample() {
        return "crud|update|Project|5\n";
    }

    @Override
    protected Integer commandPosition() {
        return 125;
    }

    @Override
    protected int getNumberCommands() {
        return 4;
    }
}
