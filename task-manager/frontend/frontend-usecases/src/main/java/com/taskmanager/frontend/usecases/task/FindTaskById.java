package com.taskmanager.frontend.usecases.task;

import com.taskmanager.domain.TaskModel;
import com.taskmanager.frontend.usecases.port.RestService;

import java.util.List;

public class FindTaskById {

    private final RestService<TaskModel> restService;

    public FindTaskById(RestService<TaskModel> restService) {
        this.restService = restService;
    }

    public TaskModel findTaskById(final int id){
        final String resource = "/task/findById/" + id;
        final TaskModel taskModel = restService.getById(resource, TaskModel.class);
        return taskModel;
    }
}
