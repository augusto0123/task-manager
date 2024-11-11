package com.taskmanager.backend.usecases.task;

import com.taskmanager.backend.usecases.port.TaskRepository;
import com.taskmanager.domain.TaskModel;

public class UpdateTask {

    private final TaskRepository taskRepository;

    public UpdateTask(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public boolean update(final TaskModel taskModel){

        final TaskModel byId = taskRepository.findById(taskModel.getId());
        byId.setName(taskModel.getName());
        byId.setDescription(taskModel.getDescription());
        byId.setDueDate(taskModel.getDueDate());
        byId.setPriority(taskModel.getPriority());
        byId.setStatus(taskModel.getStatus());

        boolean updateTask = false;
        try {
            updateTask = taskRepository.update(byId);
            return updateTask;
        } catch (Exception e){
            return true;
        }
    }

    public boolean conclude(final int id) {
        if (id <= 0){
            return false;
        }
        boolean response = taskRepository.conclude(id);
        return response;
    }

}
