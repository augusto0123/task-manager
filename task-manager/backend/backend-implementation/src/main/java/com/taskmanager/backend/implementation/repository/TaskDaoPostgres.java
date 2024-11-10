package com.taskmanager.backend.implementation.repository;

import com.taskmanager.backend.implementation.repository.connection.ConnectionFactory;
import com.taskmanager.backend.usecases.port.TaskRepository;
import com.taskmanager.domain.TaskModel;

import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TaskDaoPostgres implements TaskRepository {
    @Override
    public TaskModel findById(int id) {
        final TaskModel taskModel = new TaskModel();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "SELECT * FROM tarefa";
        sql += " WHERE id = ?;";

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                taskModel.setId(resultSet.getInt("id"));
                taskModel.setName(resultSet.getString("nome"));
                taskModel.setDescription(resultSet.getString("descricao"));
                taskModel.setDueDate(resultSet.getString("data_vencimento"));
                taskModel.setPriority(resultSet.getString("prioridade"));
                taskModel.setStatus(resultSet.getString("status"));
            }

            resultSet.close();
            preparedStatement.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return taskModel;
    }

    @Override
    public List<TaskModel> findAll() {

        final List<TaskModel> taskModels = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        final String sql = "SELECT * FROM tarefa;";

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                final TaskModel taskModel = new TaskModel();
                taskModel.setId(resultSet.getInt("id"));
                taskModel.setName(resultSet.getString("nome"));
                taskModel.setDescription(resultSet.getString("descricao"));
                taskModel.setDueDate(resultSet.getString("data_vencimento"));
                taskModel.setPriority(resultSet.getString("prioridade"));
                taskModel.setStatus(resultSet.getString("status"));

                taskModels.add(taskModel);
            }

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return taskModels;
    }

    @Override
    public List<TaskModel> findByStatus(String type) {
        final List<TaskModel> taskModels = new ArrayList<>();

        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("O status não pode ser nulo ou vazio.");
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        final String sql = "SELECT * FROM tarefa WHERE status = ?;";

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, type);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final TaskModel taskModel = new TaskModel();
                taskModel.setId(resultSet.getInt("id"));
                taskModel.setName(resultSet.getString("nome"));
                taskModel.setDescription(resultSet.getString("descricao"));
                taskModel.setDueDate(resultSet.getString("data_vencimento"));
                taskModel.setPriority(resultSet.getString("prioridade"));
                taskModel.setStatus(resultSet.getString("status"));

                taskModels.add(taskModel);
            }

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return taskModels;
    }

    @Override
    public boolean update(TaskModel taskModel) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE tarefa SET nome = ?, descricao = ?, data_vencimento = ?, prioridade = ?, status = ?";
        sql += " WHERE id = ?;";

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, taskModel.getName());
            preparedStatement.setString(2, taskModel.getDescription());
            preparedStatement.setDate(3,Date.valueOf(taskModel.getDueDate()));
            preparedStatement.setString(4, taskModel.getPriority());
            preparedStatement.setString(5, taskModel.getStatus());
            preparedStatement.setInt(6, taskModel.getId());

            preparedStatement.execute();
            preparedStatement.close();

            return false;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(int id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM tarefa";
        sql += " WHERE id = ?;";

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            preparedStatement.execute();
            preparedStatement.close();

            return false;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int create(TaskModel taskModel) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "INSERT INTO tarefa (nome, descricao, data_vencimento, prioridade, status)";
        sql += " VALUES(?, ?, ?, ?, ?)";

        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, taskModel.getName());
            preparedStatement.setString(2, taskModel.getDescription());
            preparedStatement.setDate(3,Date.valueOf(taskModel.getDueDate()));
            preparedStatement.setString(4, taskModel.getPriority());
            preparedStatement.setString(5, taskModel.getStatus());

            preparedStatement.execute();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()){
                final int id = resultSet.getInt(1);
                taskModel.setId(id);
            }

            connection.commit();
            resultSet.close();
            preparedStatement.close();

            return taskModel.getId();

        } catch (Exception e) {
            if (connection != null){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            throw new RuntimeException(e);
        }
    }

    private String formataData(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(data, formatter);
        return dateTime.toLocalDate().toString();
    }
}
