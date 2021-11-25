package ua.goit.service.handler;

import lombok.AccessLevel;
import lombok.Getter;
import ua.goit.controller.Controller;
import ua.goit.controller.MessageSender;
import ua.goit.models.Skill;
import ua.goit.service.SkillService;

import java.util.Optional;

public class HandlerCrudUpdateSkill extends CommandHandler{

    @Getter(AccessLevel.PROTECTED)
    private final String[] processedCommands = {"crud", "update", "Skill"};

    public HandlerCrudUpdateSkill(MessageSender messageSender, Controller controller) {
        super(messageSender, controller);
    }

    @Override
    protected void apply(String... command) {
        messageSender.send("Enter parametr's for update Skill: [id|field|level]");
        Skill entityForUpdate = new Skill(controller.read());
        Optional<Skill> skill = new SkillService().readById(Skill.class, Long.valueOf(command[3]));
        if (skill.isPresent()){
            messageSender.send("Updating entity: " + skill.get() + " on " + entityForUpdate);
            new SkillService().updateEntity(Skill.class, entityForUpdate);
        } else {
            messageSender.send("No such entity((");
        }
    }

    @Override
    protected String commandDescription() {
        return "Update Skill with id";
    }

    @Override
    protected String commandExample() {
        return "crud|update|Skill|5\n";
    }

    @Override
    protected Integer commandPosition() {
        return 130;
    }

    @Override
    protected int getNumberCommands() {
        return 4;
    }
}
