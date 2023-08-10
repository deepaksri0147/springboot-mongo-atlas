package com.javatechie.controller;

import com.javatechie.model.Task;
import com.javatechie.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService service;

      @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createTask(@RequestBody Task task) throws InterruptedException{
         service.addTask(task);
         return "process sent";
    }
    
    
    @PostMapping("/delay")
    public String delayTask(@RequestBody Task task) throws InterruptedException{
         service.delayTaskservice(task);
         return "process delayed";
    }

    @GetMapping
    public List<Task> getTasks() {
        return service.findAllTasks();
    }


    @GetMapping("/{taskId}")
    public Task getTask(@PathVariable String taskId){
        return service.getTaskByTaskId(taskId);
    }

    @GetMapping("/severity/{severity}")
    public List<Task> findTaskUsingSeverity(@PathVariable int severity){
        return service.getTaskBySeverity(severity);
    }

    @GetMapping("/assignee/{assignee}")
    public List<Task> getTaskByAssignee(@PathVariable String assignee){
        return service.getTaskByAssignee(assignee);
    }

    @PutMapping
    public Task modifyTask(@RequestBody Task task){
        return service.updateTask(task);
    }

    @DeleteMapping("/{taskId}")
    public String deleteTask(@PathVariable String taskId){
        return service.deleteTask(taskId);
    }
}
