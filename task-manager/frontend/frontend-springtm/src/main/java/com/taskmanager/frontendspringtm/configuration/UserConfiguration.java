package com.taskmanager.frontendspringtm.configuration;

import com.taskmanager.domain.UserModel;
import com.taskmanager.frontend.usecases.port.RestService;
import com.taskmanager.frontend.usecases.user.CreateUser;
import com.taskmanager.frontendspringtm.port.impl.RestApiController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {

    @Bean
    public CreateUser createUser(){
        RestService<UserModel> restService = new RestApiController<>();
        return new CreateUser(restService);
    }
}
