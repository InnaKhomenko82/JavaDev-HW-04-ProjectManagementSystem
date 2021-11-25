package ua.goit.service.handler;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;
import ua.goit.controller.Controller;
import ua.goit.controller.MessageSender;
import ua.goit.models.dto.DevWithLevelDto;
import ua.goit.service.ServiceQuery;

public class HandleQueryListDevsWithLevel extends CommandHandler{

    @Getter(AccessLevel.PROTECTED)
    private final String[] processedCommands = {"query", "listDevsWithLevel"};

    protected HandleQueryListDevsWithLevel(MessageSender messageSender, Controller controller) {
        super(messageSender, controller);
    }

    @SneakyThrows
    @Override
    protected void apply(String... command) {
        messageSender.send(ServiceQuery.class
                .getDeclaredMethod(command[1], String.class, Class.class)
                .invoke(new ServiceQuery(), command[2], DevWithLevelDto.class));
    }

    @Override
    protected String commandDescription() {
        return "List of Developers with SkillLevel";
    }

    @Override
    protected String commandExample() {
        return "~query|listDevsWithLevel|skillsLevel~\n" +
                "query|listDevsWithLevel|junior\n" +
                "query|listDevsWithLevel|middle\n" +
                "query|listDevsWithLevel|senior\n";
    }

    @Override
    protected Integer commandPosition() {
        return 150;
    }

    @Override
    protected int getNumberCommands() {
        return 3;
    }
}
