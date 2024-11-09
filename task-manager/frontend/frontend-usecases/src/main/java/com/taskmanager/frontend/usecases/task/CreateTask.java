package com.taskmanager.frontend.usecases.task;

import com.taskmanager.domain.TaskModel;
import com.taskmanager.frontend.usecases.port.RestService;

public class CreateTask {

    private final RestService<TaskModel> restService;
    public CreateTask(RestService<TaskModel> restService) {
        this.restService = restService;
    }
    public int createTask(TaskModel taskModel){
        final String resource = "/task/add";
        final int id = restService.post(resource, taskModel);
        return id;
    }
}
