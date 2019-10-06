package tech.artos.service.impl;

import tech.artos.model.Task;
import tech.artos.repository.TaskJpaRepository;
import tech.artos.service.interfaces.TaskService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskJpaRepository taskJpaRepository;
    private final Logger logger = Logger.getLogger(TaskServiceImpl.class);

    @Autowired
    public TaskServiceImpl(TaskJpaRepository taskJpaRepository) {
        this.taskJpaRepository = taskJpaRepository;
    }

    @Override
    @Transactional
    public void addTask(Task task) {
        task.setDate(LocalDateTime.now());
        taskJpaRepository.save(task);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getAllTasks() {
        return taskJpaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Task> getTaskById(Long id) {
        return taskJpaRepository.findById(id);
    }

    @Override
    @Transactional
    public void updateTask(Task task) {
        Optional<Task> taskById = taskJpaRepository.findById(task.getId());
        if (taskById.isPresent()) {
            taskById.get().setTitle(task.getTitle());
            taskById.get().setInformation(task.getInformation());
            taskById.get().setStatus(task.isStatus());
        } else {
            logger.error("Task with ID: " + task.getId() + " not found.");
        }
    }

    @Override
    @Transactional
    public void deleteTask(Task task) {
        taskJpaRepository.delete(task);
    }
}
