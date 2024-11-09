package com.taskmanager.backend.implementation.repository;

import com.taskmanager.backend.implementation.repository.connection.ConnectionFactory;
import com.taskmanager.backend.usecases.port.UserRepository;
import com.taskmanager.domain.UserModel;

import javax.swing.plaf.nimbus.State;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoPostgres implements UserRepository {
    @Override
    public UserModel findById(int id) {

        final UserModel userModel = new UserModel();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "SELECT * FROM usuario";
        sql += " WHERE id = ?;";

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                userModel.setId(resultSet.getInt("id"));
                userModel.setName(resultSet.getString("nome"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setPassword(resultSet.getString("senha"));
                userModel.setPasswordConfirm(resultSet.getString("confirmar_senha"));
                userModel.setType(resultSet.getString("tipo"));
            }

            resultSet.close();
            preparedStatement.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return userModel;
    }

    @Override
    public List<UserModel> findAll() {

        final List<UserModel> userModels = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        final String sql = "SELECT * FROM usuario;";

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                final UserModel userModel = new UserModel();
                userModel.setId(resultSet.getInt("id"));
                userModel.setName(resultSet.getString("nome"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setPassword(resultSet.getString("senha"));
                userModel.setPasswordConfirm(resultSet.getString("confirmar_senha"));
                userModel.setType(resultSet.getString("tipo"));

                userModels.add(userModel);
            }

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userModels;
    }

    @Override
    public boolean update(UserModel userModel) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "UPDATE usuario SET email = ?, senha = ?, confirmar_senha = ?";
        sql += "WHERE id = ?;";

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, userModel.getEmail());
            preparedStatement.setString(2, userModel.getPassword());
            preparedStatement.setString(3, userModel.getPasswordConfirm());
            preparedStatement.setInt(4, userModel.getId());

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

        String sql = "DELETE FROM usuario";
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
    public int create(UserModel userModel) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "INSERT INTO usuario (nome, email, senha, confirmar_senha, tipo)";
        sql += " VALUES(?, ?, ?, ?, ?)";

        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, userModel.getName());
            preparedStatement.setString(2, userModel.getEmail());
            preparedStatement.setString(3, userModel.getPassword());
            preparedStatement.setString(4, userModel.getPasswordConfirm());
            preparedStatement.setString(5, userModel.getType());

            preparedStatement.execute();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()){
                final int id =resultSet.getInt(1);
                userModel.setId(id);
            }

            connection.commit();
            resultSet.close();
            preparedStatement.close();

            return userModel.getId();

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
}
