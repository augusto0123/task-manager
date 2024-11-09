package com.taskmanager.backend.usecases.user;

import com.taskmanager.backend.usecases.port.UserRepository;
import com.taskmanager.domain.UserModel;

public class CreateUser {

    private final UserRepository userRepository;

    public CreateUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public int createUser(final UserModel userModel){
        if (userModel == null){
            return -1;
        }

        if (userModel.getName().isEmpty()
                || userModel.getEmail().isEmpty()
                || userModel.getPassword().isEmpty()
                || userModel.getPasswordConfirm().isEmpty()
                || userModel.getType().isEmpty()){
            return -1;
        }
        return userRepository.create(userModel);
    }
}
