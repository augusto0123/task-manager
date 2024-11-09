package com.taskmanager.backend.springtm.configuration;

import com.taskmanager.backend.implementation.repository.UserDaoPostgres;
import com.taskmanager.backend.usecases.port.UserRepository;
import com.taskmanager.backend.usecases.user.CreateUser;
import org.springframework.context.annotation.Bean;

public class UserBackendConfiguration {

    private final UserRepository userRepository;

    public UserBackendConfiguration() {
        this.userRepository = new UserDaoPostgres();
    }

    @Bean
    public CreateUser createUser(){
        return new CreateUser(userRepository);
    }
}
