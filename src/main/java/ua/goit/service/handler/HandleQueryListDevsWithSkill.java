package ua.goit.service.handler;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;
import ua.goit.controller.Controller;
import ua.goit.controller.MessageSender;
import ua.goit.models.dto.DevWithSkillDto;
import ua.goit.service.ServiceQuery;

public class HandleQueryListDevsWithSkill extends CommandHandler {

    @Getter(AccessLevel.PROTECTED)
    private final String[] processedCommands = {"query", "listDevsWithSkill"};

    protected HandleQueryListDevsWithSkill(MessageSender messageSender, Controller controller, CommandExecutor executor) {
        super(messageSender, controller, executor);
    }

    @SneakyThrows
    @Override
    protected void apply(String... command) {
        messageSender.send(ServiceQuery.class
                .getDeclaredMethod(command[1], String.class, Class.class)
                .invoke(new ServiceQuery(), command[2], DevWithSkillDto.class));
    }

    @Override
    protected String commandDescription() {
        return "List of Developers with SkillField";
    }

    @Override
    protected String commandExample() {
        return "~query|listDevsWithSkill|SkillField~\n" +
                "query|listDevsWithSkill|Java\n" +
                "query|listDevsWithSkill|C++\n" +
                "query|listDevsWithSkill|C#\n" +
                "query|listDevsWithSkill|JS\n";
    }

    @Override
    protected Integer commandPosition() {
        return 145;
    }

    @Override
    protected int getNumberCommands() {
        return 3;
    }
}
