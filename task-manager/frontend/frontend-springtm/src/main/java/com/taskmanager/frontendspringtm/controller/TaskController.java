package com.taskmanager.frontendspringtm.controller;

import com.taskmanager.domain.TaskModel;
import com.taskmanager.frontend.usecases.task.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final CreateTask createTask;
    private final FindAllTasks findAllTasks;
    private final FindTaskById findTaskById;
    private final UpdateTask updateTask;
    private final DeleteTask deleteTask;

    public TaskController(CreateTask createTask, FindAllTasks findAllTasks, FindTaskById findTaskById, UpdateTask updateTask, DeleteTask deleteTask) {
        this.createTask = createTask;
        this.findAllTasks = findAllTasks;
        this.findTaskById = findTaskById;
        this.updateTask = updateTask;
        this.deleteTask = deleteTask;
    }

    @GetMapping("/all")
    public String getAllTasksPage(final Model model){
        List<TaskModel> taskModels = findAllTasks.findAllTasks();
        if (taskModels == null){
            taskModels = new ArrayList<>();
        }

        model.addAttribute("tasks", taskModels);
        return "/task/all-tasks";
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
}
