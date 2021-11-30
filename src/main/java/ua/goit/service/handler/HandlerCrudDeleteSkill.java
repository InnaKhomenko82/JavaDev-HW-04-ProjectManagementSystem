package ua.goit.service.handler;

import lombok.AccessLevel;
import lombok.Getter;
import ua.goit.controller.Controller;
import ua.goit.controller.MessageSender;
import ua.goit.models.Skill;
import ua.goit.service.SkillService;

import java.util.Optional;

public class HandlerCrudDeleteSkill extends CommandHandler{
    @Getter(AccessLevel.PROTECTED)
    private final String[] processedCommands = {"crud", "delete", "Skill"};

    protected HandlerCrudDeleteSkill(MessageSender messageSender, Controller controller, CommandExecutor executor) {
        super(messageSender, controller, executor);
    }

    @Override
    protected void apply(String... command) {
        Optional<Skill> skill = new SkillService().readById(Skill.class, Long.valueOf(command[3]));
        if (skill.isPresent()){
            System.out.println("Deleting entity: " + skill.get());
            new SkillService().deleteById(Skill.class, Long.valueOf(command[3]));
        } else {
            System.out.println("No such entity((");
        }
    }

    @Override
    protected String commandDescription() {
        return "Delete Skill by id";
    }

    @Override
    protected String commandExample() {
        return "~crud|delete|Skill|SkillId~\n" +
                "crud|delete|Skill|5\n";
    }

    @Override
    protected Integer commandPosition() {
        return 100;
    }

    @Override
    protected int getNumberCommands() {
        return 4;
    }
}
