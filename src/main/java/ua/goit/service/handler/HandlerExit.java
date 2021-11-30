package ua.goit.service.handler;

import lombok.AccessLevel;
import lombok.Getter;
import ua.goit.controller.Controller;
import ua.goit.controller.MessageSender;

public class HandlerExit extends CommandHandler {

    @Getter(AccessLevel.PROTECTED)
    private final String[] processedCommands = {"exit"};

    protected HandlerExit(MessageSender messageSender, Controller controller, CommandExecutor executor) {
        super(messageSender, controller, executor);
    }

    @Override
    protected void apply(String[] command) {
        messageSender.send("Have a good day)))");
        System.exit(0);
    }

    @Override
    protected String commandDescription() {
        return "Stop the application";
    }

    @Override
    protected String commandExample() {
        return "exit\n";
    }

    @Override
    protected Integer commandPosition() {
        return 1000;
    }

    @Override
    protected int getNumberCommands() {
        return 1;
    }
}
