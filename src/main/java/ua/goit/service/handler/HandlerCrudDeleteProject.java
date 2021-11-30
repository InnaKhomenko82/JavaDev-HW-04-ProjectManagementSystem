package ua.goit.service.handler;

import lombok.AccessLevel;
import lombok.Getter;
import ua.goit.controller.Controller;
import ua.goit.controller.MessageSender;
import ua.goit.models.Project;
import ua.goit.service.ProjectService;

import java.util.Optional;

public class HandlerCrudDeleteProject extends CommandHandler{
    @Getter(AccessLevel.PROTECTED)
    private final String[] processedCommands = {"crud", "delete", "Project"};

    protected HandlerCrudDeleteProject(MessageSender messageSender, Controller controller, CommandExecutor executor) {
        super(messageSender, controller, executor);
    }

    @Override
    protected void apply(String... command) {
        Optional<Project> project = new ProjectService().readById(Project.class, Long.valueOf(command[3]));
        if (project.isPresent()){
            System.out.println("Deleting entity: " + project.get());
            new ProjectService().deleteById(Project.class, Long.valueOf(command[3]));
        } else {
            System.out.println("No such entity((");
        }
    }

    @Override
    protected String commandDescription() {
        return "Delete Project by id";
    }

    @Override
    protected String commandExample() {
        return "~crud|delete|Project|ProjectId~\n" +
                "crud|delete|Project|5\n";
    }

    @Override
    protected Integer commandPosition() {
        return 95;
    }

    @Override
    protected int getNumberCommands() {
        return 4;
    }
}
