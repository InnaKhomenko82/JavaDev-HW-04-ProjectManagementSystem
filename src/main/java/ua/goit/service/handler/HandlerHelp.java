package ua.goit.service.handler;

import lombok.AccessLevel;
import lombok.Getter;
import ua.goit.controller.Controller;
import ua.goit.controller.MessageSender;

import java.util.Comparator;
import java.util.stream.Collectors;

public class HandlerHelp extends CommandHandler {

    @Getter(AccessLevel.PROTECTED)
    private final String[] processedCommands = {"help"};

    private String helpList;

    public HandlerHelp(MessageSender messageSender, Controller controller, CommandExecutor executor) {
        super(messageSender, controller, executor);
    }

    @Override
    protected void apply(String... command) {
        if (helpList==null) helpList = createCommandHelp();
        messageSender.send(helpList);
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

    private String createCommandHelp(){
        return executor.getHandlers().stream().sorted(Comparator.comparing(CommandHandler::commandPosition))
                .map(commandHandler -> String.join("\n", commandHandler.commandDescription(),
                        commandHandler.commandExample()))
                .collect(Collectors.joining("\n"));
    }
}