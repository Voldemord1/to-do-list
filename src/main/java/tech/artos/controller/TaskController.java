package tech.artos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import tech.artos.model.Task;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import tech.artos.util.TaskFilterUtil;
import tech.artos.service.interfaces.TaskService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class TaskController {

    private TaskService taskService;
    private TaskFilterUtil taskFilterUtil;
    private static final Logger logger = Logger.getLogger(TaskController.class);

    @Autowired
    public TaskController(TaskService taskService, TaskFilterUtil taskFilterUtil) {
        this.taskService = taskService;
        this.taskFilterUtil = taskFilterUtil;
    }

    @RequestMapping("/all_tasks")
    public String getAllTasks(Model model) {
        List<Task> allTasks = taskService.getAllTasks();
        model.addAttribute("allTasks", allTasks);
        return "tasks";
    }

    @RequestMapping("/delete")
    public String deleteTask(@RequestParam("id") String taskId) {
        if (taskId != null) {
            Optional<Task> taskById = taskService.getTaskById(Long.parseLong(taskId));
            taskById.ifPresent(task -> taskService.deleteTask(task));
        }
        return "redirect:/admin/all_tasks";
    }

    @GetMapping("/create")
    public String createTask(Model model) {
        model.addAttribute("task", new Task());
        return "new_task";
    }

    @PostMapping("/create")
    public String newTask(Model model, @ModelAttribute Task task) {
        if (!task.getTitle().isEmpty()) {
            taskService.addTask(task);
            return "redirect:/admin/all_tasks";
        } else {
            model.addAttribute("error", true);
            return "new_task";
        }
    }

    @GetMapping("/info")
    public String infoTask(Model model, @RequestParam("id") String taskId) {
        if (taskId != null) {
            Optional<Task> taskById = taskService.getTaskById(Long.parseLong(taskId));
            taskById.ifPresent(task -> model.addAttribute("task", taskById.get()));
        } else {
            model.addAttribute("error", true);
            logger.warn("ID not passed");
            return "admin/info";
        }
        return "info";
    }

    @PostMapping("/info")
    public String saveTask(Model model, @ModelAttribute Task task) {
        if (Objects.nonNull(task)) {
            taskService.updateTask(task);
            return "redirect:/admin/all_tasks";
        } else {
            model.addAttribute("error", true);
            return "info";
        }
    }

    @RequestMapping("/all_tasks/filter")
    public String filterTasks(Model model) {
        List<Task> allTasks;
        if(taskFilterUtil.isFiltered()) {
            allTasks = taskService.getAllTasks()
                    .stream()
                    .filter(Task::isStatus)
                    .collect(Collectors.toList());
            taskFilterUtil.setFiltered(false);
        }else {
            allTasks = taskService.getAllTasks()
                    .stream()
                    .filter(task -> !task.isStatus())
                    .collect(Collectors.toList());
            taskFilterUtil.setFiltered(true);
        }
        model.addAttribute("allTasks", allTasks);
        return "tasks";
    }
}
