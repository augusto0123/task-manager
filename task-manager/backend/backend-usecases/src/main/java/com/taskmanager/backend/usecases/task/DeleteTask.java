package com.taskmanager.backend.usecases.task;

import com.taskmanager.backend.usecases.port.TaskRepository;

public class DeleteTask {
    private final TaskRepository taskRepository;

    public DeleteTask(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public boolean delete(final int id){
        if (id <= 0){
            return false;
        }
        boolean response = taskRepository.deleteById(id);
        return response;
    }
}
