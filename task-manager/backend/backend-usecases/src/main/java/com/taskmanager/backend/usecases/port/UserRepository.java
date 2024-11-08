package com.taskmanager.backend.usecases.port;

import com.taskmanager.domain.UserModel;

import java.util.List;

public interface UserRepository {

    UserModel findById(final int id);
    List<UserModel> findAll();
    boolean update(final UserModel userModel);
    boolean deleteById(final int id);
    int create(final UserModel userModel);
}
