package ua.goit.service.handler;

import lombok.AccessLevel;
import lombok.Getter;
import ua.goit.controller.Controller;
import ua.goit.controller.MessageSender;
import ua.goit.models.Project;
import ua.goit.service.ProjectService;

import java.util.Optional;

public class HandlerCrudReadProjectByID extends CommandHandler{

    @Getter(AccessLevel.PROTECTED)
    private final String[] processedCommands = {"crud", "read", "Project"};

    public HandlerCrudReadProjectByID(MessageSender messageSender, Controller controller) {
        super(messageSender, controller);
    }

    @Override
    protected void apply(String... command) {
        Optional<Project> project = new ProjectService().readById(Project.class, Long.valueOf(command[3]));
        messageSender.send(project.isPresent() ? project.get()  : "No such entity((");
    }

    @Override
    protected String commandDescription() {
        return "Read Project by id";
    }

    @Override
    protected String commandExample() {
        return "~crud|read|Project|ProjectId~\n" +
                "crud|read|Project|3\n";
    }

    @Override
    protected Integer commandPosition() {
        return 70;
    }

    @Override
    protected int getNumberCommands() {
        return 4;
    }
}
