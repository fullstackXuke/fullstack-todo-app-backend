     package com.example.backend.controller;

     import com.example.demo.entity.Task;
     import com.example.demo.repository.TaskRepository;
     import org.springframework.beans.factory.annotation.Autowired;
     import org.springframework.web.bind.annotation.*;

     import java.util.List;

     @RestController
     @RequestMapping("/api/tasks")
     public class TaskController {

         @Autowired
         private TaskRepository taskRepository;

         @GetMapping
         public List<Task> getAllTasks() {
             return taskRepository.findAll();
         }

         @PostMapping
         public Task createTask(@RequestBody Task task) {
             return taskRepository.save(task);
         }

         @PutMapping("/{id}")
         public Task updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
             Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
             task.setTitle(taskDetails.getTitle());
             task.setDescription(taskDetails.getDescription());
             return taskRepository.save(task);
         }

         @DeleteMapping("/{id}")
         public void deleteTask(@PathVariable Long id) {
             Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
             taskRepository.delete(task);
         }
     }
