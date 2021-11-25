package ua.goit.repository;

import ua.goit.models.BaseEntity;

import java.util.List;

public interface QueryRepository<T extends BaseEntity> {

    List <T> salaryByProjectName(String projectName, Class T);

    List <T> listDevsInProject(String projectName, Class T);

    List <T> listDevsWithSkill(String skillName, Class T);

    List <T> listDevsWithLevel(String levelName, Class T);

    List <T> listOfProjects(Class T);
}
