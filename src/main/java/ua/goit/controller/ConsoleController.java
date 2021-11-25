package ua.goit.controller;

import ua.goit.service.handler.CommandExecutor;

import java.util.Arrays;
import java.util.Scanner;

public class ConsoleController implements Controller, MessageSender{

    private final Scanner scanner = new Scanner(System.in);

    public void mainMenu() {

        CommandExecutor commandExecutor = new CommandExecutor(this, this);

        System.out.println("Hello, friend!");
        while (true) {
            System.out.println("Choose your command, 'help' or 'exit':");
            commandExecutor.handle(read());
        }
    }

    @Override
    public String[] read() {
        String command = scanner.next();
        return command.split("\\|");
    }

    @Override
    public void send(Object... message) {
        Arrays.stream(message).forEach(System.out::println);
    }
}
