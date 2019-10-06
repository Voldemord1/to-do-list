package tech.artos.service.interfaces;

import tech.artos.model.Task;
import java.util.List;
import java.util.Optional;

public interface TaskService {

    void addTask(Task task);

    List<Task> getAllTasks();

    Optional<Task> getTaskById(Long id);

    void updateTask(Task task);

    void deleteTask(Task task);
}
