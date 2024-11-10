package com.taskmanager.frontend.usecases.task;

import com.taskmanager.domain.TaskModel;
import com.taskmanager.frontend.usecases.port.RestService;

import java.util.List;

public class FindTaskByStatus {

    private final RestService<TaskModel> restService;

    public FindTaskByStatus(RestService<TaskModel> restService) {
        this.restService = restService;
    }

    public List<TaskModel> findTaskByStatus(final String status){
        final String resource = "/task/findByStatus/" + status;
        final List<TaskModel> taskModels = restService.get(resource);
        return taskModels;
    }
}
