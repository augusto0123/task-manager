package com.taskmanager.frontendspringtm.configuration;

import com.taskmanager.domain.TaskModel;
import com.taskmanager.frontend.usecases.port.RestService;
import com.taskmanager.frontend.usecases.task.*;
import com.taskmanager.frontendspringtm.port.impl.RestApiController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskConfiguration {

    @Bean
    public CreateTask createTask(){
        RestService<TaskModel> restService = new RestApiController<>();
        return new CreateTask(restService);
    }

    @Bean
    public FindAllTasks findAllTasks(){
        RestService<TaskModel> restService = new RestApiController<>();
        return new FindAllTasks(restService);
    }

    @Bean
    public FindTaskById findTaskById(){
        RestService<TaskModel> restService = new RestApiController<>();
        return new FindTaskById(restService);
    }

    @Bean
    public FindTaskByStatus findTaskByStatus(){
        RestService<TaskModel> restService = new RestApiController<>();
        return new FindTaskByStatus(restService);
    }

    @Bean
    public UpdateTask updateTask(){
        RestService<TaskModel> restService = new RestApiController<>();
        return new UpdateTask(restService);
    }

    @Bean
    public DeleteTask deleteTask(){
        RestService<TaskModel> restService = new RestApiController<>();
        return new DeleteTask(restService);
    }
}
