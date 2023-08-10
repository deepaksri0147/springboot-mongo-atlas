package com.javatechie.service;

import com.javatechie.model.Task;
import com.javatechie.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    //CRUD  CREATE , READ , UPDATE , DELETE


       public String addTask(Task task) throws InterruptedException {
    	for(int i=0; i<10;i++) {
    		Thread.sleep(10000);
    		task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
    		task.setSeverity(i);
    		repository.save(task);
    		System.out.println(i);
    	}
        
        return "processes sucessfully";
    }
    
    
    public String delayTaskservice(Task task) throws InterruptedException {
   	task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
    	for(int i=0; i<15;i++) {
    		Thread.sleep(5000);
    		System.out.println(i);
    	}
    	repository.save(task);
        return "processes sucessfully";
    }

    public List<Task> findAllTasks() {
        return repository.findAll();
    }

    public Task getTaskByTaskId(String taskId){
        return repository.findById(taskId).get();
    }

    public List<Task> getTaskBySeverity(int severity){
        return  repository.findBySeverity(severity);
    }

    public List<Task> getTaskByAssignee(String assignee){
        return repository.getTasksByAssignee(assignee);
    }

    public Task updateTask(Task taskRequest){
        //get the existing document from DB
        // populate new value from request to existing object/entity/document
        Task existingTask = repository.findById(taskRequest.getTaskId()).get();
        existingTask.setDescription(taskRequest.getDescription());
        existingTask.setSeverity(taskRequest.getSeverity());
        existingTask.setAssignee(taskRequest.getAssignee());
        existingTask.setStoryPoint(taskRequest.getStoryPoint());
        return repository.save(existingTask);
    }

    public String deleteTask(String taskId){
        repository.deleteById(taskId);
        return taskId+" task deleted from dashboard ";
    }
}
