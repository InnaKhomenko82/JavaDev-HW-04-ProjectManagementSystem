package ua.goit.service.handler;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;
import org.reflections.Reflections;
import ua.goit.controller.Controller;
import ua.goit.controller.MessageSender;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class CommandExecutor {

    @Getter(AccessLevel.PROTECTED)
    private final List<CommandHandler> handlers;

    public CommandExecutor(MessageSender messageSender, Controller controller) {
        this.handlers = createCommandHandler(messageSender, controller);
    }

    public final void handle(String[] command) {
        handlers.stream()
                .filter(handler -> isApplicable(handler, command)).findAny()
                .orElseThrow(() -> new RuntimeException("Your command is unknown: " + Arrays.toString(command)))
                .apply(command);
    }

    private boolean isApplicable(CommandHandler handler, String[] commands) {
        String[] processedCommands = handler.getProcessedCommands();
        if (processedCommands==null || processedCommands.length==0) return false;
        if (commands.length != handler.getNumberCommands()) return false;
        for (int i = 0; i < processedCommands.length; i++) {
            if (!processedCommands[i].equals(commands[i])) return false;
        }
        return true;
    }

    @SneakyThrows
    private List<CommandHandler> createCommandHandler(MessageSender messageSender, Controller controller) {
        Reflections reflections = new Reflections("ua.goit");
        Set<Class<? extends CommandHandler>> classes = reflections.getSubTypesOf(CommandHandler.class);
        List<CommandHandler> commandHandlers = new ArrayList<>(classes.size());
        for (Class<? extends CommandHandler> clazz : classes) {
            if (!Modifier.isAbstract(clazz.getModifiers())) {
                CommandHandler handler = clazz.getDeclaredConstructor(MessageSender.class, Controller.class, CommandExecutor.class)
                        .newInstance(messageSender, controller, this);
                commandHandlers.add(handler);
            }
        }
        return commandHandlers;
    }
}