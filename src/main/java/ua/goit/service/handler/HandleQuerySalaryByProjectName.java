package ua.goit.service.handler;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;
import ua.goit.controller.Controller;
import ua.goit.controller.MessageSender;
import ua.goit.models.dto.SalaryByProjectNameDto;
import ua.goit.service.ServiceQuery;

public class HandleQuerySalaryByProjectName extends CommandHandler{

    @Getter(AccessLevel.PROTECTED)
    private final String[] processedCommands = {"query", "salaryByProjectName"};


    public HandleQuerySalaryByProjectName(MessageSender messageSender, Controller controller) {
        super(messageSender, controller);
    }

    @SneakyThrows
    @Override
    protected void apply(String... command) {
        messageSender.send(ServiceQuery.class
                .getDeclaredMethod(command[1], String.class, Class.class)
                .invoke(new ServiceQuery(), command[2], SalaryByProjectNameDto.class));
    }

    @Override
    protected String commandDescription() {
        return "Developers salary in a Project";
    }

    @Override
    protected String commandExample() {
        return "~query|salaryByProjectName|ProjectName~\n" +
                "query|salaryByProjectName|ShedullerBot\n" +
                "query|salaryByProjectName|NavigationBot\n" +
                "query|salaryByProjectName|TrainingBot\n" +
                "query|salaryByProjectName|LectionBot\n";
    }

    @Override
    protected Integer commandPosition() {
        return 135;
    }

    @Override
    protected int getNumberCommands() {
        return 3;
    }
}
