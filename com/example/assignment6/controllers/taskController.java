package com.example.assignment6.controllers;

import com.example.assignment6.model.ResponseAPI;
import com.example.assignment6.model.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class taskController {

    private ArrayList<Task> tasks = new ArrayList<>();

    @GetMapping("task")
    public ResponseEntity<ArrayList<Task>> getTasks() {
        return ResponseEntity.status(200).body(tasks);
    }

    @PostMapping("task")
    public ResponseEntity<ResponseAPI> addTasks(@RequestBody Task task){
        tasks.add(task);
        return ResponseEntity.status(200).body(new ResponseAPI("Task " + task.getTitle() + " added"));
    }

    @PutMapping("task/{id}")
    public ResponseEntity<ResponseAPI> editTasks(@PathVariable String id){
        for (int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).getId().equals(id)) {
                tasks.get(i).setStatus("Done");
                return ResponseEntity.status(200).body(new ResponseAPI("Your status is " + tasks.get(i).getStatus()));
            }
        }
        return ResponseEntity.status(400).body(new ResponseAPI("The id is not exist "));
    }

    @GetMapping("task/{title}")
    public ResponseEntity<ResponseAPI> searchTitle(@PathVariable String title) {
        for (int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).getTitle().equals(title)){
                return ResponseEntity.status(200).body(new ResponseAPI("The title is: " + tasks.get(i).getTitle()));
            }
        }
        return ResponseEntity.status(400).body(new ResponseAPI("The title is not exist "));

    }

    @GetMapping("task/{id}")
    public ResponseEntity<ResponseAPI> searchId(@PathVariable String id) {
        for (int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).getId().equals(id)){
                return ResponseEntity.status(200).body(new ResponseAPI("The id is: " + tasks.get(i).getId()));
            }
        }
        return ResponseEntity.status(400).body(new ResponseAPI("The id is not exist "));
    }

    @GetMapping("task/{index}")
    public ResponseEntity<ResponseAPI> getStatus(@PathVariable Integer index) {
        for (int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).getStatus().equals("Not_Done")){
                return ResponseEntity.status(200).body(new ResponseAPI("The Not_Done tasks is: " + tasks.get(i).getTitle()));
            }
        }

        return ResponseEntity.status(400).body(new ResponseAPI("The status is not exist "));

    }

}
