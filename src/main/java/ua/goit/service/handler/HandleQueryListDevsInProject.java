package ua.goit.service.handler;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;
import ua.goit.controller.Controller;
import ua.goit.controller.MessageSender;
import ua.goit.models.dto.DevInProjectDto;
import ua.goit.service.ServiceQuery;

public class HandleQueryListDevsInProject extends CommandHandler{

    @Getter(AccessLevel.PROTECTED)
    private final String[] processedCommands = {"query", "listDevsInProject"};

    protected HandleQueryListDevsInProject(MessageSender messageSender, Controller controller) {
        super(messageSender, controller);
    }

    @SneakyThrows
    @Override
    protected void apply(String... command) {
        messageSender.send(ServiceQuery.class
                .getDeclaredMethod(command[1], String.class, Class.class)
                .invoke(new ServiceQuery(), command[2], DevInProjectDto.class));
    }

    @Override
    protected String commandDescription() {
        return "List of developers in a project";
    }

    @Override
    protected String commandExample() {
        return "~query|listDevsInProject|ProjectName~\n" +
                "query|listDevsInProject|ShedullerBot\n" +
                "query|listDevsInProject|NavigationBot\n" +
                "query|listDevsInProject|TrainingBot\n" +
                "query|listDevsInProject|LectionBot\n";
    }

    @Override
    protected Integer commandPosition() {
        return 140;
    }

    @Override
    protected int getNumberCommands() {
        return 3;
    }
}
