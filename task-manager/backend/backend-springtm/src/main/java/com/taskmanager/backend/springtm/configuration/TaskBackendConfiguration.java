package com.taskmanager.backend.springtm.configuration;

import com.taskmanager.backend.implementation.repository.TaskDaoPostgres;
import com.taskmanager.backend.usecases.port.TaskRepository;
import com.taskmanager.backend.usecases.task.CreateTask;
import com.taskmanager.backend.usecases.task.DeleteTask;
import com.taskmanager.backend.usecases.task.FindTask;
import com.taskmanager.backend.usecases.task.UpdateTask;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskBackendConfiguration {

    private final TaskRepository taskRepository;

    public TaskBackendConfiguration() {
        this.taskRepository = new TaskDaoPostgres();
    }

    @Bean
    public CreateTask createTask(){
        return new CreateTask(taskRepository);
    }

    @Bean
    public FindTask findTask(){
        return new FindTask(taskRepository);
    }

    @Bean
    public UpdateTask updateTask(){
        return new UpdateTask(taskRepository);
    }

    @Bean
    public DeleteTask deleteTask(){
        return new DeleteTask(taskRepository);
    }
}
