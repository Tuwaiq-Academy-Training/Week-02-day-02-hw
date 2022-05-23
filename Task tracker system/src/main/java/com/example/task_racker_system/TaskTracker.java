package com.example.task_racker_system;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TaskTracker {


    List<Task> tasks = new ArrayList<>();

    @PostMapping("task")
    public ResponseEntity<ResponsApi> createNewTask(@RequestBody Task task) {
        tasks.add(task);
        return ResponseEntity.status(200).body(new ResponsApi(" your task is added"));

    }

    @GetMapping("task")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("task/{taskId}/{taskStatus}")
    public ResponseEntity<ResponsApi> changeTaskStatus(@PathVariable String taskId, @PathVariable String taskStatus) {
        if(taskStatus != null && !(taskStatus.equals("done") || taskStatus.equals("notDone") )) {
            return ResponseEntity.status(400).body(new ResponsApi("invalid task status"));
        }
        Task foundTask = null;
        for (Task task : tasks) {
            if(task.getId().equals(taskId)) {
                foundTask = task;
                break;
            }
        }
        if(foundTask == null) {
            return ResponseEntity.status(400).body(new ResponsApi("invalid task id"));
        }
        foundTask.setStatus(taskStatus);
        return ResponseEntity.status(200).body(new ResponsApi("task status is update to "+taskStatus));

    }

    @GetMapping("task/{title}")
    public ResponseEntity<Task> searchByTaskTitle(@PathVariable String title) {
        Task foundTask = null;
        for (Task task : tasks) {
            if(task.getTitle().equals(title)) {
                foundTask = task;
                break;
            }
        }
        if(foundTask == null) {
            return ResponseEntity.status(400).body(null);
        }
        return ResponseEntity.ok(foundTask);
    }

    @GetMapping("task-person/{personID}")
    public ResponseEntity<Task> searchByPerson(@PathVariable String personID) {
        Task foundTask = null;
        for (Task task : tasks) {
            if(task.getPerson().getId().equals(personID)) {
                foundTask = task;
                break;
            }
        }
        if(foundTask == null) {
            return ResponseEntity.status(400).body(null);
        }
        return ResponseEntity.ok(foundTask);
    }

    @GetMapping("task-notDone")
    public ResponseEntity<List<Task>> notDoneTasks() {
        List<Task> notDoneTasks = new ArrayList<>();
        for (Task task : tasks) {
            if(task.getStatus().equals("notDone")) {
                notDoneTasks.add(task);
            }
        }
        if(notDoneTasks.size() == 0) {
            return ResponseEntity.status(400).body(null);
        }
        return ResponseEntity.ok(notDoneTasks);
    }
}
