package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Task;
import com.example.demo.enumIdentifier.Status;
import com.example.demo.service.TaskService;

@RestController
@RequestMapping("/task")
@CrossOrigin
public class TaskController {

	@Autowired
	TaskService taskService;
	
	   @GetMapping
	    public List<Task> getAllTasks() {
	       // return taskRepository.findAll()
		   
		   return taskService.fetchAll();
			
	    }
	   
	    @PostMapping
	    public ResponseEntity<Task> createTask(@RequestBody Task task) {
	    	var savedTask = taskService.creatTask(task);
	    	 return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
	    }
	    
	    @PutMapping("/{id}/status")
	    public ResponseEntity<Task> updateTaskStatus(
	            @PathVariable Long id,
	            @RequestParam Status status) {
			try {
				System.out.println("Update task trigger"+ status);
				var updatedTask = taskService.updateTaskStatus(id, status);
				return new ResponseEntity<>(updatedTask, HttpStatus.OK);
			} catch (RuntimeException ex) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
	    }
}
