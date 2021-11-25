package ua.goit.service;

import ua.goit.models.BaseEntity;
import ua.goit.models.dto.*;
import ua.goit.repository.QueryRepositoryImpl;

import java.util.List;

public class ServiceQuery<T extends BaseEntity>  {
    public List<T> salaryByProjectName(String projectName, Class<T> tClass){
        return new QueryRepositoryImpl(SalaryByProjectNameDto.class).salaryByProjectName(projectName, tClass);
    }

    public List <T> listDevsInProject(String projectName, Class<T> tClass){
        return new QueryRepositoryImpl(DevInProjectDto.class).listDevsInProject(projectName, tClass);
    }

    public List <T> listDevsWithSkill(String skillName, Class<T> tClass) {
        return new QueryRepositoryImpl(DevWithSkillDto.class).listDevsWithSkill(skillName, tClass);
    }

    public List <T> listDevsWithLevel(String levelName, Class<T> tClass) {
        return new QueryRepositoryImpl(DevWithLevelDto.class).listDevsWithLevel(levelName, tClass);
    }

    public List <T> listOfProjects(Class<T> tClass) {
        return new QueryRepositoryImpl(ProjectDto.class).listOfProjects(tClass);
    }
}
