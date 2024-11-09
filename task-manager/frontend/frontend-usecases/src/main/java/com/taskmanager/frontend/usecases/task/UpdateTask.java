package com.taskmanager.frontend.usecases.task;

import com.taskmanager.domain.TaskModel;
import com.taskmanager.frontend.usecases.port.RestService;

public class UpdateTask {

    private final RestService<TaskModel> restService;

    public UpdateTask(RestService<TaskModel> restService) {
        this.restService = restService;
    }

    public boolean updateTask(TaskModel taskModel){
        final String resource = "/task/update/" + taskModel.getId();
        boolean result = restService.put(resource, taskModel);
        return result;
    }
}
