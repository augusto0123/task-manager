package com.taskmanager.backend.usecases.port;

import com.taskmanager.domain.TaskModel;

import java.util.List;

public interface TaskRepository {

    TaskModel findById(final int id);
    List<TaskModel> findAll();
    List<TaskModel> findByStatus(String type);
    boolean update(final TaskModel taskModel);
    boolean deleteById(final int id);
    int create(final TaskModel taskModel);
    boolean conclude(final int id);

}
