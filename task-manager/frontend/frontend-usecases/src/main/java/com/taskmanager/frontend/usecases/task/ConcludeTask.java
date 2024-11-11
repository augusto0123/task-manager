package com.taskmanager.frontend.usecases.task;

import com.taskmanager.domain.TaskModel;
import com.taskmanager.frontend.usecases.port.RestService;

public class ConcludeTask {

    private final RestService<TaskModel> restService;

    public ConcludeTask(RestService<TaskModel> restService) {
        this.restService = restService;
    }

    public boolean concludeTask(int id){

        if (id <= 0){
            return false;
        }

        final String resource = "/task/conclude/" + id;
        return restService.put(resource, null);
    }
}
