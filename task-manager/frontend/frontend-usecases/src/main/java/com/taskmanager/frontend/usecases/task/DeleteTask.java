package com.taskmanager.frontend.usecases.task;

import com.taskmanager.domain.TaskModel;
import com.taskmanager.frontend.usecases.port.RestService;

public class DeleteTask {

    private final RestService<TaskModel> restService;

    public DeleteTask(RestService<TaskModel> restService) {
        this.restService = restService;
    }

    public boolean deleteTask(final int id){
        if (id <= 0){
            return false;
        }
        final String resource = "/task/delete/" + id;
        return restService.delete(resource);
    }
}
