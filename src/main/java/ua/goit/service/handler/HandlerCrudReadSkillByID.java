package ua.goit.service.handler;

import lombok.AccessLevel;
import lombok.Getter;
import ua.goit.controller.Controller;
import ua.goit.controller.MessageSender;
import ua.goit.models.Skill;
import ua.goit.service.SkillService;

import java.util.Optional;

public class HandlerCrudReadSkillByID extends CommandHandler{

    @Getter(AccessLevel.PROTECTED)
    private final String[] processedCommands = {"crud", "read", "Skill"};

    public HandlerCrudReadSkillByID(MessageSender messageSender, Controller controller) {
        super(messageSender, controller);
    }

    @Override
    protected void apply(String... command) {
        Optional<Skill> skill = new SkillService().readById(Skill.class, Long.valueOf(command[3]));
        messageSender.send(skill.isPresent() ? skill.get()  : "No such entity((");
    }

    @Override
    protected String commandDescription() {
        return "Read Skill by id";
    }

    @Override
    protected String commandExample() {
        return "~crud|read|Skill|SkillId~\n" +
                "crud|read|Skill|2\n";
    }

    @Override
    protected Integer commandPosition() {
        return 75;
    }

    @Override
    protected int getNumberCommands() {
        return 4;
    }
}
