package ua.goit.controller;

@FunctionalInterface
public interface MessageSender {
    void send(Object... message);
}
