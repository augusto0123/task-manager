package com.taskmanager.backend.springtm.restcontroller;

import com.taskmanager.backend.springtm.configuration.UserBackendConfiguration;
import com.taskmanager.domain.TaskModel;
import com.taskmanager.domain.UserModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private final UserBackendConfiguration userBackendConfiguration = new UserBackendConfiguration();

    @GetMapping("/add")
    public int getCreateUser(@RequestBody UserModel userModel){
        return userBackendConfiguration.createUser().createUser(userModel);
    }
}
