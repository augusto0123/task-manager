package com.taskmanager.frontend.usecases.user;

import com.taskmanager.domain.UserModel;
import com.taskmanager.frontend.usecases.port.RestService;

public class CreateUser {

    private final RestService<UserModel> restService;
    public CreateUser(RestService<UserModel> restService) {
        this.restService = restService;
    }

    public int createUser(final UserModel userModel){
        final String resource = "/user/add";
        return restService.post(resource, userModel);
    }
}
