package ua.goit.service.handler;

import lombok.AccessLevel;
import lombok.Getter;
import ua.goit.controller.Controller;
import ua.goit.controller.MessageSender;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HandlerHelp extends CommandHandler {

    @Getter(AccessLevel.PROTECTED)
    private final String[] processedCommands = {"help"};

    public HandlerHelp(MessageSender messageSender, Controller controller) {
        super(messageSender, controller);
    }

    @Override
    protected void apply(String... command) {
        List<CommandHandler> commandHandlers = CommandExecutor.getHandlers();
        messageSender.send(commandHandlers.stream().sorted(Comparator.comparing(o -> o.commandPosition()))
                .map(commandHandler -> String.join("\n", commandHandler.commandDescription(),
                        commandHandler.commandExample()))
                .collect(Collectors.joining("\n")));
    }

    @Override
    protected String commandDescription() {
        return "List of all commands";
    }

    @Override
    protected String commandExample() {
        return "help\n";
    }

    @Override
    protected Integer commandPosition() {
        return 999;
    }

    @Override
    protected int getNumberCommands() {
        return 1;
    }
}
