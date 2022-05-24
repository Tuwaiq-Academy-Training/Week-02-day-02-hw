package com.example.hw1_02_02.controller;

import com.example.hw1_02_02.model.ApiResponse;
import com.example.hw1_02_02.model.Person;
import com.example.hw1_02_02.model.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
public class TaskController {


    private ArrayList<Task> tasks = new ArrayList<>();


    @GetMapping("task")
    public ResponseEntity<ArrayList<Task>> getCustomers() {
        return ResponseEntity.status(200).body(tasks);
    }

    @PostMapping("task")
    public ResponseEntity<ApiResponse> addTask(@RequestBody Task task) {
        tasks.add(task);
        return ResponseEntity.status(201).body(new ApiResponse("tasks", 201));
    }

    @PutMapping("task/{index}")
    public ResponseEntity<ApiResponse> updateTask(@RequestBody Task task,
                                                  @PathVariable Integer index) {
        if (index >= tasks.size() || index < 0) {
            return ResponseEntity.status(400).body(new ApiResponse("Invalid index", 400));
        }
        tasks.set(index, task);
        return ResponseEntity.status(200).body(new ApiResponse(" updated", 200));
    }

    @DeleteMapping("/{index}")
    public ResponseEntity<ApiResponse> deleteTask(@PathVariable Integer index) {
        if (index >= tasks.size() || index < 0) {
            return ResponseEntity.status(400).body(new ApiResponse("Invalid index", 400));
        }
        tasks.remove((int) index);
        return ResponseEntity.status(200).body(new ApiResponse(" deleted", 200));
    }

}
