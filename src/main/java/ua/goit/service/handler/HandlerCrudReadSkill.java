package ua.goit.service.handler;

import lombok.AccessLevel;
import lombok.Getter;
import ua.goit.controller.Controller;
import ua.goit.controller.MessageSender;
import ua.goit.models.Skill;
import ua.goit.service.SkillService;

public class HandlerCrudReadSkill extends CommandHandler{
    @Getter(AccessLevel.PROTECTED)
    private final String[] processedCommands = {"crud", "read", "Skill"};

    public HandlerCrudReadSkill(MessageSender messageSender, Controller controller) {
        super(messageSender, controller);
    }

    @Override
    protected void apply(String... command) {
        messageSender.send(new SkillService().findAll(Skill.class));
    }

    @Override
    protected String commandDescription() {
        return "Read all Skills";
    }

    @Override
    protected String commandExample() {
        return "crud|read|Skill\n";
    }

    @Override
    protected Integer commandPosition() {
        return 50;
    }
}
