package com.taskmanager.backend.implementation.repository;

import com.taskmanager.backend.implementation.repository.connection.ConnectionFactory;
import com.taskmanager.backend.usecases.port.UserRepository;
import com.taskmanager.domain.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        return 0;
    }
}
