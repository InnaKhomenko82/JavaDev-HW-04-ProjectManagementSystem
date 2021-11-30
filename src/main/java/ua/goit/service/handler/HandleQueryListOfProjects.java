package ua.goit.service.handler;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;
import ua.goit.controller.Controller;
import ua.goit.controller.MessageSender;
import ua.goit.models.dto.ProjectDto;
import ua.goit.service.ServiceQuery;

public class HandleQueryListOfProjects extends CommandHandler{

    @Getter(AccessLevel.PROTECTED)
    private final String[] processedCommands = {"query", "listOfProjects"};

    protected HandleQueryListOfProjects(MessageSender messageSender, Controller controller, CommandExecutor executor) {
        super(messageSender, controller, executor);
    }

    @SneakyThrows
    @Override
    protected void apply(String... command) {
        messageSender.send(ServiceQuery.class
                .getDeclaredMethod(command[1], Class.class)
                .invoke(new ServiceQuery(), ProjectDto.class));
    }

    @Override
    protected String commandDescription() {
        return "List of Projects";
    }

    @Override
    protected String commandExample() {
        return "query|listOfProjects\n";
    }

    @Override
    protected Integer commandPosition() {
        return 155;
    }
}
