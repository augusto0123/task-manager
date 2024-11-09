package com.taskmanager.frontend.usecases.task;

import com.taskmanager.domain.TaskModel;
import com.taskmanager.frontend.usecases.port.RestService;

import java.util.List;

public class FindAllTasks {

    private final RestService<TaskModel> restService;
    public FindAllTasks(RestService<TaskModel> restService) {
        this.restService = restService;
    }
    public List<TaskModel> findAllTasks(){
        final String resource = "/task/all";
        final List<TaskModel> taskModels = restService.get(resource);
        return taskModels;
    }
}
