package ua.goit.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import ua.goit.controller.DataBaseConnection;
import lombok.SneakyThrows;
import ua.goit.models.BaseEntity;
import ua.goit.util.PropertiesLoader;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryRepositoryImpl<T extends BaseEntity> implements QueryRepository<T>, Closeable{

    private final Connection connection;
    private final String databaseSchemaName;
    private final ObjectMapper mapper;
    private final Class<T> tClass;

    private final PreparedStatement salaryByProjectNamePreparedStatement;
    private final PreparedStatement listDevsInProjectPreparedStatement;
    private final PreparedStatement listDevsWithSkillPreparedStatement;
    private final PreparedStatement listDevsWithLevelPreparedStatement;
    private final PreparedStatement listOfProjectsPreparedStatement;

    @SneakyThrows
    public QueryRepositoryImpl(Class <T> tClass) {
        this.connection = DataBaseConnection.getInstance().getConnection();
        this.databaseSchemaName = PropertiesLoader.getProperty("schemaName");
        this.mapper = new ObjectMapper();
        this.tClass = tClass;

        this.salaryByProjectNamePreparedStatement = connection.prepareStatement(
                "SELECT p.name, sum(d.salary) as totalSalary FROM " + databaseSchemaName + "." + "developers d" +
                " INNER JOIN developers_projects dp on d.id = dp.developer_id" +
                " INNER JOIN projects p on p.id = dp.project_id" +
                " where p.name = ?;");

        this.listDevsInProjectPreparedStatement = connection.prepareStatement(
                "SELECT p.name as projectName, d.name as devName\n" +
                "FROM " + databaseSchemaName + "." + "developers d\n" +
                "INNER JOIN developers_projects dp on d.id = dp.developer_id\n" +
                "INNER JOIN projects p on p.id = dp.project_id\n" +
                "where p.name = ?;");

                this.listDevsWithSkillPreparedStatement = connection.prepareStatement(
                        "SELECT s.field as skillName, d.name as devName\n" +
                "FROM " + databaseSchemaName + "." + "developers d\n" +
                "INNER JOIN developers_skills ds on d.id = ds.developer_id\n" +
                "INNER JOIN skills s on s.id = ds.skill_id\n" +
                "where s.field = ?;");

       this.listDevsWithLevelPreparedStatement = connection.prepareStatement(
               "SELECT s.level as level, d.name as devName\n" +
                "FROM " + databaseSchemaName + "." + "developers d\n" +
                "INNER JOIN developers_skills ds on d.id = ds.developer_id\n" +
                "INNER JOIN skills s on s.id = ds.skill_id\n" +
                "where s.level = ?;");
        this.listOfProjectsPreparedStatement = connection.
                prepareStatement("SELECT p.start, p.name, count(d.name) as quantityDevs\n" +
                "FROM " + databaseSchemaName + "." + "projects p\n" +
                "INNER JOIN developers_projects dp on p.id = dp.project_id\n" +
                "INNER JOIN developers d on d.id = dp.developer_id\n" +
                "group by p.name;");
    }

    @SneakyThrows
    @Override
    public List <T> salaryByProjectName(String projectName, Class T) {
       salaryByProjectNamePreparedStatement.setObject(1, projectName);
       return parse(salaryByProjectNamePreparedStatement.executeQuery(), tClass);
    }

    @SneakyThrows
    @Override
    public List <T> listDevsInProject(String projectName, Class T) {
        listDevsInProjectPreparedStatement.setObject(1, projectName);
        return parse(listDevsInProjectPreparedStatement.executeQuery(), tClass);
    }

    @SneakyThrows
    @Override
    public List <T> listDevsWithSkill(String skillName, Class T) {
        listDevsWithSkillPreparedStatement.setObject(1, skillName);
        return parse(listDevsWithSkillPreparedStatement.executeQuery(), tClass);
    }

    @SneakyThrows
    @Override
    public List <T> listDevsWithLevel(String levelName, Class T) {
        listDevsWithLevelPreparedStatement.setObject(1, levelName);
        return parse(listDevsWithLevelPreparedStatement.executeQuery(), tClass);
    }

    @SneakyThrows
    @Override
    public List <T> listOfProjects(Class T) {
        return parse(listOfProjectsPreparedStatement.executeQuery(), tClass);
    }

    @SneakyThrows
    @Override
    public void close() {
        connection.close();
    }

    @SneakyThrows
    private<T> List<T> parse(ResultSet resultSet, Class<T> modelClass){
        final List<T> list = new ArrayList<>();
        while (resultSet.next()){
            final Map<String,Object> objectMap = new HashMap<>(resultSet.getMetaData().getColumnCount());
            for (int i=1; i<=resultSet.getMetaData().getColumnCount(); i++){
                objectMap.put(resultSet.getMetaData().getColumnLabel(i), resultSet.getObject(i));
            }
            list.add(mapper.convertValue(objectMap, modelClass));
        }
        return list;
    }
}