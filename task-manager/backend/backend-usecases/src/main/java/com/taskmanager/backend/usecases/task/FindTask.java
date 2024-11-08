package com.taskmanager.backend.usecases.task;

import com.taskmanager.backend.usecases.exception.InvalidException;
import com.taskmanager.backend.usecases.exception.NotFoundException;
import com.taskmanager.backend.usecases.port.TaskRepository;
import com.taskmanager.domain.TaskModel;

import java.util.List;

public class FindTask {

    private final TaskRepository taskRepository;

    public FindTask(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskModel> findAll(){
        final List<TaskModel> taskModels = taskRepository.findAll();
        if (taskModels == null){
            return null;
        }
        return taskModels;
    }

    public TaskModel findById(final int id){
        if (id < 0){
            throw new InvalidException();
        }
        TaskModel taskModel = taskRepository.findById(id);
        if (taskModel == null){
            final String message = "O id (" + id + ") não foi encontrado";
            throw new NotFoundException(message);
        }
        return taskModel;
    }
}
