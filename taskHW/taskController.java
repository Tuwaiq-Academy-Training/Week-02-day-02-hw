package com.example.javaDay02.controllers;

import com.example.javaDay02.model.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
@RequestMapping("task/")
public class taskController {
    ArrayList <Task> tasks = new ArrayList<>();
    @PostMapping("add")
    public ResponseEntity addTask(@RequestBody Task task){
        tasks.add(task);
        return ResponseEntity.status(201).body("Task created");
    }
    @GetMapping("")
    public ResponseEntity getTasks(){
        return ResponseEntity.status(200).body(tasks);
    }
    @PutMapping("status/{id}")
    public ResponseEntity taskStatus(@PathVariable String id){
        for(Task task : tasks){
            if(task.getID().equals(id)){
                if(task.getStatus().equals("done")){
                    task.setStatus("unDone");
                    return ResponseEntity.status(201).body("Task is set to unDone");
                }
                else {task.setStatus("done");
                return ResponseEntity.status(201).body("Task is set to Done");}
            }
        }
        return ResponseEntity.status(400).body("Couldn't find task");
    }
    @GetMapping("tasktitle/{title}")
    public ResponseEntity getTaskByTitle(@PathVariable String title){
        ArrayList taskTitle = new ArrayList<>();
        for( Task task: tasks){
            if(task.getTitle().equals(title) ){
                taskTitle.add(task);

            }
        }
        if(!taskTitle.isEmpty()){
            return ResponseEntity.status(201).body(taskTitle);
        }
        return ResponseEntity.status(400).body("Couldn't find the task");
    }
    @GetMapping("personid/{id}")
    public ResponseEntity getTaskByPersonID(@PathVariable String id){
        ArrayList personTasks = new ArrayList<>();
        for(Task task :tasks){
            if(task.getPerson().getID().equals(id)){
                personTasks.add(task);}


        }
        if(!personTasks.isEmpty()){
            return ResponseEntity.status(201).body(personTasks);
        }
        return ResponseEntity.status(400).body("Couldn't find the task");
    }
    @GetMapping("undone")
    public ResponseEntity getUnDoneTask(){
        ArrayList unDone = new ArrayList();
        for (Task task :tasks){
            if(task.getStatus().equals("unDone")){
                 unDone.add(task);

            }
        }
        if(!unDone.isEmpty()){
            return ResponseEntity.status(201).body(unDone);
        }
        return ResponseEntity.status(201).body("All tasks are done");
    }

}
