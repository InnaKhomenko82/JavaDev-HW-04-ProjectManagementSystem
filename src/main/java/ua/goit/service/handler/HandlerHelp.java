package ua.goit.service.handler;

import lombok.AccessLevel;
import lombok.Getter;
import ua.goit.controller.Controller;
import ua.goit.controller.MessageSender;

public class HandlerHelp extends CommandHandler {

    @Getter(AccessLevel.PROTECTED)
    private final String[] processedCommands = {"help"};

    public HandlerHelp(MessageSender messageSender, Controller controller) {
        super(messageSender, controller);
    }

    @Override
    protected void apply(String... command) {
        messageSender.send(CommandExecutor.createCommandHelp());
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
