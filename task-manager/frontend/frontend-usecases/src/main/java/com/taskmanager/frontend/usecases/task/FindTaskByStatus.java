package com.taskmanager.frontend.usecases.task;

import com.taskmanager.domain.TaskModel;
import com.taskmanager.frontend.usecases.port.RestService;

import java.util.List;

public class FindTaskByStatus {

    private final RestService<TaskModel> restService;

    public FindTaskByStatus(RestService<TaskModel> restService) {
        this.restService = restService;
    }

    public List<TaskModel> findTaskByStatus(final int id){
        final String resource = "/task/all-completed";
        final List<TaskModel> taskModels = restService.get(resource);
        return taskModels;
    }
}
