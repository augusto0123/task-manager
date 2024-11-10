package com.taskmanager.backend.springtm.restcontroller;

import com.taskmanager.backend.springtm.configuration.TaskBackendConfiguration;
import com.taskmanager.domain.TaskModel;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskRestController {

    private final TaskBackendConfiguration taskBackendConfiguration = new TaskBackendConfiguration();

    @GetMapping("/all")
    public List<TaskModel> getAllTasks(){
        List<TaskModel> taskModels = taskBackendConfiguration.findTask().findByStatus("Em Andamento");
        return taskModels;
    }

    @GetMapping("/all-completed")
    public List<TaskModel> getAllCompletedTasks(){
        List<TaskModel> taskModels = taskBackendConfiguration.findTask().findByStatus("Concluída");
        return taskModels;
    }

    @GetMapping("/findById/{id}")
    public TaskModel getTaskById(@PathVariable("id") final int id){
        TaskModel taskModel = taskBackendConfiguration.findTask().findById(id);
        return taskModel;
    }

    @PutMapping("/update")
    public boolean getUpdateTask(@RequestBody TaskModel taskModel){
        return taskBackendConfiguration.updateTask().update(taskModel);
    }

    @PostMapping("/add")
    public int getCreateTask(@RequestBody TaskModel taskModel){
        return taskBackendConfiguration.createTask().createTask(taskModel);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") final int id){
        return taskBackendConfiguration.deleteTask().delete(id);
    }
}
