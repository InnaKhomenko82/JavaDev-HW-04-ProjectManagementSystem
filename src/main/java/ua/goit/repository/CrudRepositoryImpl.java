package ua.goit.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import ua.goit.controller.DataBaseConnection;
import lombok.SneakyThrows;
import ua.goit.models.BaseEntity;
import ua.goit.util.PropertiesLoader;

import javax.persistence.*;
import java.io.Closeable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CrudRepositoryImpl <ID, E extends BaseEntity<ID>> implements CrudRepository<ID, E>, Closeable {

    private final Connection connection;
    private final String databaseSchemaName;
    private final ObjectMapper mapper;
    private final Class<E> modelClass;
    private final Map<String,String> columnFieldName;

    private final PreparedStatement findAllPreparedStatement;
    private final PreparedStatement findByIdPreparedStatement;
    private final PreparedStatement deletePreparedStatement;
    private final PreparedStatement createPreparedStatement;
    private final PreparedStatement updatePreparedStatement;

    @SneakyThrows
    public CrudRepositoryImpl(Class <E> modelClass){
        this.connection = DataBaseConnection.getInstance().getConnection();
        this.databaseSchemaName = PropertiesLoader.getProperty("schemaName");
        this.mapper = new ObjectMapper();
        this.modelClass = modelClass;
        this.columnFieldName = Arrays.stream(this.modelClass.getDeclaredFields())
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .collect(Collectors.toMap(field -> getColumnName(field), field -> field.getName()));

        String[] generatedColumns = {getColumnName(Arrays.stream(this.modelClass.getDeclaredFields())
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .filter(modelField -> modelField.getAnnotation(Id.class) != null)
                .findAny().orElseThrow(() -> new RuntimeException("Entity must contains ID")))};

        String tableName = modelClass.getAnnotation(Table.class) != null
                ? modelClass.getAnnotation(Table.class).name() : modelClass.getSimpleName().toLowerCase();

        String countValues = IntStream.range(0, columnFieldName.size()).mapToObj(p -> "?").collect(Collectors.joining(","));
        String fieldsForCreate = columnFieldName.keySet().stream().collect(Collectors.joining(","));
        String fieldsForUpdate = columnFieldName.keySet().stream().map(p -> p+"=?").collect(Collectors.joining(","));

        this.findAllPreparedStatement = connection.prepareStatement(
                "SELECT * FROM " + databaseSchemaName + "." + tableName, generatedColumns);
        this.findByIdPreparedStatement = connection.prepareStatement(
                "SELECT * FROM " + databaseSchemaName + "." + tableName + " WHERE " + generatedColumns[0] + " = ?;", generatedColumns);
        this.deletePreparedStatement = connection.prepareStatement(
                "DELETE FROM " + databaseSchemaName + "." + tableName + " WHERE " + generatedColumns[0] + " = ?;", generatedColumns);
        this.createPreparedStatement = connection.prepareStatement(
                "INSERT INTO " + databaseSchemaName + "." + tableName +
                       "(" + fieldsForCreate + ")" + " VALUES (" + countValues + ")", generatedColumns);
        this.updatePreparedStatement = connection.prepareStatement(
                "UPDATE " + databaseSchemaName + "." + tableName +
                        " SET " + fieldsForUpdate + " WHERE " + generatedColumns[0] + " = ?;", generatedColumns);
    }

    private String getColumnName(Field field) {
        return field.getAnnotation(Column.class) == null? field.getName() : field.getAnnotation(Column.class).name();
    }

    @SneakyThrows
    @Override
    public List<E> findAll() {
        return parse(findAllPreparedStatement.executeQuery());
    }

    @SneakyThrows
    @Override
    public Optional<E> findById(ID id) {
        findByIdPreparedStatement.setObject(1,id);
        final List<E> result = parse(findByIdPreparedStatement.executeQuery());
        if (result.isEmpty()) return Optional.empty();
        if (result.size() > 1) throw  new RuntimeException("returned more than one result");
        return Optional.of(result.get(0));
    }

    @SneakyThrows
    @Override
    public void deleteById (ID id) {
        deletePreparedStatement.setObject(1, id);
        deletePreparedStatement.executeUpdate();
    }

    @Override
    public List<E> saveAll(Iterable<E> itrb) {
        final List<E> result = new ArrayList<>();
        for (E e: itrb) result.add(save(e));
        return result;
    }

    @SneakyThrows
    @Override
    public E save(E e) {
        if (e.getId() == null || !findById(e.getId()).isPresent()) {
            return executeStatement(createPreparedStatement, e);
        } else {
            updatePreparedStatement.setObject(columnFieldName.size() + 1, e.getId());
            return executeStatement(updatePreparedStatement, e);
        }
    }

    @SneakyThrows
    private List<E> parse(ResultSet resultSet) {
        final List<E> list = new ArrayList<>();
        while (resultSet.next()) {
            final Map<String,Object> objectMap = new HashMap<>();
            for (String fieldName : columnFieldName.keySet()){
                objectMap.put(columnFieldName.get(fieldName), resultSet.getObject(fieldName));
            }
            list.add(mapper.convertValue(objectMap,modelClass));
        }
        return list;
    }

    @SneakyThrows
    private E executeStatement(PreparedStatement statement, E e) {
        int count = 1;
        for (String fieldName : columnFieldName.values()) {
            Field declaredField = modelClass.getDeclaredField(fieldName);
            declaredField.setAccessible(true);
            statement.setObject(count++, declaredField.get(e));
        }
        statement.executeUpdate();
        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next()) {
            return findById((ID) rs.getObject(1)).get();
        }
        return findById(e.getId()).get();
    }

    @SneakyThrows
    @Override
    public void close() {
        connection.close();
    }
}
