package com.taskmanager.frontendspringtm.controller;

import com.taskmanager.domain.TaskModel;
import com.taskmanager.frontend.usecases.task.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/task")
public class TaskController {

    private final CreateTask createTask;
    private final FindAllTasks findAllTasks;
    private final FindTaskById findTaskById;

    private final FindTaskByStatus findTaskByStatus;
    private final UpdateTask updateTask;
    private final DeleteTask deleteTask;

    public TaskController(CreateTask createTask, FindAllTasks findAllTasks, FindTaskById findTaskById, FindTaskByStatus findTaskByStatus, UpdateTask updateTask, DeleteTask deleteTask) {
        this.createTask = createTask;
        this.findAllTasks = findAllTasks;
        this.findTaskById = findTaskById;
        this.findTaskByStatus = findTaskByStatus;
        this.updateTask = updateTask;
        this.deleteTask = deleteTask;
    }

    @GetMapping("/all")
    public String getAllTasksPage(final Model model){
        List<TaskModel> taskModels = findTaskByStatus.findTaskByStatus("Em Andamento");
        if (taskModels == null){
            taskModels = new ArrayList<>();
        }

        model.addAttribute("tasks", taskModels);
        return "/task/all-tasks";
    }

    @GetMapping("/all-completed")
    public String getAllCompletedTasksPage(final Model model){
        return "/task/completed-tasks";
    }

    @GetMapping("/edit")
    public String getEditTaskPage(){
        return "/task/edit-task";
    }

    @PostMapping("/update")
    public String getUpdateTaskPage(final TaskModel taskModel){
        boolean result = updateTask.updateTask(taskModel);
        if (!result){
            return "redirect:/task/edit-task";
        }
        return "redirect:/not-found";
    }

    @GetMapping("/create")
    public String getCreateTaskPage(final Model model){
        model.addAttribute("task", new TaskModel());
        return "/task/create-task";
    }

    @PostMapping("/add")
    public String createTask(final TaskModel taskModel){
        final int id = createTask.createTask(taskModel);
        if (id > 0){
            return "redirect:/task/all";
        }
        return "redirect:/not-found";
    }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") int id) {
        boolean result = deleteTask.deleteTask(id);
        if (!result) {
            return "redirect:/task/all";
        }
        return "redirect:/not-found";
    }
}
