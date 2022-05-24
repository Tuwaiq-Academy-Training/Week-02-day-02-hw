package com.demo.HW2.task;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
public class TaskTrackerSystem {
    private ArrayList<Task> tasks = new ArrayList<>();
    public LocalDate deadline(){
        return LocalDate.now();
    }
    @GetMapping()
    public ResponseEntity getTask(){
        return ResponseEntity.status(200).body(tasks);
    }

    @PostMapping()
    public ResponseEntity addTask(@RequestBody Task task){
        tasks.add(task);
        return ResponseEntity.status(201).body(task);
    }

    @PutMapping("/{index}")
    public ResponseEntity<TaskAPI> updateTask(@PathVariable Integer index,@RequestBody Task status){
        if (status.getStatus().equals("notDone")){
            tasks.set(index,status);
            return ResponseEntity.status(400).body(
                    new TaskAPI("Task: "+status.getStatus()+"  Edit",200));
        } else {
            return ResponseEntity.status(200).body(
                    new TaskAPI("Request is bad!",400));
        }
    }
    @GetMapping("/{status}")
    public ResponseEntity<ArrayList<Task>> taskTitle(@RequestBody Task status){
        if (status.getStatus().length() > 5){
            return ResponseEntity.status(400).body(tasks);
        }else {
            return ResponseEntity.status(200).body(tasks);
        }
    }
    @GetMapping("/{status}/{notDone}")
    public ResponseEntity<ArrayList<Task>> taskTitleNotDone(@RequestBody Task status){
        if (status.getStatus().equals("notDone")){
            return ResponseEntity.status(400).body(tasks);
        }else {
            return ResponseEntity.status(200).body(tasks);
        }
    }
    @GetMapping("/{index}/person")
    public ResponseEntity<ArrayList<Task>> taskPerson(@PathVariable Integer index,@RequestBody Task request){
        try {
            return ResponseEntity.status(200).body(tasks);
        } catch (Exception e){
            return ResponseEntity.status(201).body(tasks);
        }
    }
}
