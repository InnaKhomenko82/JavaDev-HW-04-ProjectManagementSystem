package ua.goit.service.handler;

import lombok.AccessLevel;
import lombok.Getter;
import ua.goit.controller.Controller;
import ua.goit.controller.MessageSender;
import ua.goit.models.Skill;
import ua.goit.service.SkillService;

public class HandlerCrudCreateSkill extends CommandHandler{
    @Getter(AccessLevel.PROTECTED)
    private final String[] processedCommands = {"crud", "create", "Skill"};

    public HandlerCrudCreateSkill(MessageSender messageSender, Controller controller, CommandExecutor executor) {
        super(messageSender, controller, executor);
    }

    @Override
    protected void apply(String... command) {
        messageSender.send("Enter parametr's for new Skill: [id|field|level]");
        Skill entityForCreate = new Skill(controller.read());
        new SkillService().createEntity(Skill.class, entityForCreate);
        messageSender.send("Created new entity: " + entityForCreate);
    }

    @Override
    protected String commandDescription() {
        return "Create new Skill";
    }

    @Override
    protected String commandExample() {
        return "crud|create|Skill\n" +
                "~id|field|level~\n" +
                "5|HTML|middle\n";
    }

    @Override
    protected Integer commandPosition() {
        return 25;
    }
}
