package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Task;
import com.example.demo.enumIdentifier.Status;
import com.example.demo.repo.TaskRepo;

@Service
public class TaskService {
	
	@Autowired
	TaskRepo taskRepository;
	
	public Task creatTask(Task task) {
       
		// Set default status
	    task.setStatus(Status.TODO);
	    System.out.println("Api Triggered");
	    Task savedTask = taskRepository.save(task);
	    return savedTask;
	   
	}
	
	
	  public Task updateTaskStatus(Long id, Status status) {
	        Task task = taskRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
	        
	        Status currentStatus = task.getStatus();

	        if (!currentStatus.canTransitionTo(status)) {
	            throw new RuntimeException(
	                    "Cannot move task from " + currentStatus + " to " + status
	            );
	        }

	        task.setStatus(status);
	        return taskRepository.save(task);
	    }
	  
	  
	  public List<Task> fetchAll(){
		 return taskRepository.findAll();
	  }

}
