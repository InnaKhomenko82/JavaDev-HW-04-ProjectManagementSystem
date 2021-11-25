package ua.goit.service.handler;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import ua.goit.controller.Controller;
import ua.goit.controller.MessageSender;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class CommandHandler {
    protected final MessageSender messageSender;

    protected final Controller controller;

    protected abstract void apply(String... command);

    protected abstract String[] getProcessedCommands();

    protected abstract String commandDescription();

    protected abstract String commandExample();

    protected abstract Integer commandPosition();

    protected int getNumberCommands(){
        return getProcessedCommands().length;
    };
}
