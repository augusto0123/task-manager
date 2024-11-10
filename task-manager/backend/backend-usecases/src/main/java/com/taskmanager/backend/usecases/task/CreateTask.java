package com.taskmanager.backend.usecases.task;

import com.taskmanager.backend.usecases.port.TaskRepository;
import com.taskmanager.domain.TaskModel;

public class CreateTask {

    private final TaskRepository taskRepository;
    public CreateTask(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    public int createTask(final TaskModel taskModel){
        if (taskModel == null){
            return -1;
        }
        if (taskModel.getName().isEmpty()
                || taskModel.getDescription().isEmpty()
                || taskModel.getDueDate().isEmpty()
                || taskModel.getPriority().isEmpty()
                || taskModel.getStatus().isEmpty()){
            return -1;
        }
        int id = 0;
        try {
            id = taskRepository.create(taskModel);
            taskModel.setId(id);
        }catch (Exception e){
            return -1;
        }
        return id;
    }

}
