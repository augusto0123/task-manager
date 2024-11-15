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

    private final ConcludeTask concludeTask;

    public TaskController(CreateTask createTask, FindAllTasks findAllTasks, FindTaskById findTaskById, FindTaskByStatus findTaskByStatus, UpdateTask updateTask, DeleteTask deleteTask, ConcludeTask concludeTask) {
        this.createTask = createTask;
        this.findAllTasks = findAllTasks;
        this.findTaskById = findTaskById;
        this.findTaskByStatus = findTaskByStatus;
        this.updateTask = updateTask;
        this.deleteTask = deleteTask;
        this.concludeTask = concludeTask;
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
        List<TaskModel> taskModels = findTaskByStatus.findTaskByStatus("Concluída");
        if (taskModels == null){
            taskModels = new ArrayList<>();
        }

        model.addAttribute("tasks", taskModels);
        return "/task/completed-tasks";
    }

    @GetMapping("/edit/{id}")
    public String getEditTaskPage(@PathVariable("id") int id, final Model model){

        TaskModel taskModel = findTaskById.findTaskById(id);
        if (taskModel == null){
            return "redirect:/not-found";
        }
        model.addAttribute("task", taskModel);
        return "/task/edit-task";
    }

    @PostMapping("/update")
    public String getUpdateTask(TaskModel taskModel){
        boolean result = updateTask.updateTask(taskModel);
        if (!result){
            return "redirect:/task/all";
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

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id")final int id) {
        final boolean result = deleteTask.deleteTask(id);
        return "redirect:/task/all";
    }

    @GetMapping("/conclude/{id}")
    public String concludeTask(@PathVariable("id") int id){
        boolean result = concludeTask.concludeTask(id);
        if (!result){
            return "redirect:/task/all";
        }
        return "redirect:/not-found";
    }
}
