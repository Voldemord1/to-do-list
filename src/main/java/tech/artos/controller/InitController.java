package tech.artos.controller;

import tech.artos.model.Task;
import tech.artos.service.interfaces.TaskService;
import tech.artos.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Controller
public class InitController {

    private TaskService taskService;
    private UserService userService;

    @Autowired
    public InitController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @PostConstruct
    private void init() {
        userService.addUser("admin@gmail.com", "123", "ROLE_ADMIN");
        userService.addUser("user@gmail.com", "123", "ROLE_USER");

        LocalDateTime localDate1 = LocalDateTime.of(2019, 10, 4, 15, 6);
        LocalDateTime localDate2 = LocalDateTime.of(2019, 10, 5, 10,25);
        LocalDateTime localDate3 = LocalDateTime.of(2019, 10, 6, 11,37);
        Task task1 = new Task("To wash a car", "description1", localDate1, true);
        Task task2 = new Task("To-do homework", "description2", localDate2, false);
        Task task3 = new Task("Write poem", "description3", localDate3, true);
        taskService.addTask(task1);
        taskService.addTask(task2);
        taskService.addTask(task3);
    }
}
