package com.example.tasks.Controller;

import com.example.tasks.Model.ResponseAPI;
import com.example.tasks.Model.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class TasksController {
    ArrayList<Task> tasks = new ArrayList<>();

    @PostMapping("task")
    public ResponseEntity<ResponseAPI> addTasks(@RequestBody Task task) {
        System.out.println(task);
        this.tasks.add(task);
        return ResponseEntity.status(201).body(new ResponseAPI("Task Added Successfully!"));
    }

    @GetMapping("task")
    public ResponseEntity<ArrayList<Task>> getTasks() {
        return ResponseEntity.status(201).body(tasks);
    }

    @PutMapping("task/status/{id}")
    public ResponseEntity<ResponseAPI> changeStatus(@PathVariable Integer id,@RequestBody String status) {
        Task task = null;
        int index = 0;
        for (Task task2 : tasks) {
            int id2 = Integer.parseInt(task2.getId());
            if (id2 == id) {
                task = task2;
                break;
            }
            index++;
        }
        if (task == null) {
            return ResponseEntity.status(400).body(new ResponseAPI("Wrong ID Provided!"));
        }
        if (status.equals("done") || status.equals("notDone")) {
            if (status.equals("done")) {
                task.setStatus(Task.statusEnum.done);
            }else {
                task.setStatus(Task.statusEnum.notDone);
            }
            return ResponseEntity.status(200).body(new ResponseAPI("Status has been updated!"));
        }else{
            return ResponseEntity.status(400).body(new ResponseAPI("Bad Request - Send done or notDone!"));
        }
    }

    @GetMapping("task/{title}")
    public ResponseEntity<Object> getOneTaskByTitle(@PathVariable String title) {
        Task returnedTask = null;
        for (Task task : tasks) {
            if (task.getTitle().equals(title)) {
                returnedTask = task;
            }
        }
        if(returnedTask != null) {
            return ResponseEntity.status(200).body(returnedTask);
        } else {
            return ResponseEntity.status(400).body(new ResponseAPI("Bad Request - title doesn't exist"));
        }
    }

    @GetMapping("task/person/{index}")
    public ResponseEntity<Object> getOneTaskByGivenId(@PathVariable String index) {
        Task returnedTask = null;
        for (Task task : tasks) {
            if (task.getPerson().getID().equals(index)) {
                returnedTask = task;
            }
        }
        if(returnedTask != null) {
            return ResponseEntity.status(200).body(returnedTask);
        } else {
            return ResponseEntity.status(400).body(new ResponseAPI("Bad Request - id doesn't exist"));
        }
    }

    @GetMapping("task/notDone")
    public ResponseEntity<Object> getAllNotDoneTasks() {
        if (tasks.size() == 0) {
            return ResponseEntity.status(400).body(new ResponseAPI("Bad Request - No Tasks!"));
        }
        ArrayList<Task> newTasks = new ArrayList<>();
        for (Task task : tasks) {
            if(task.getStatus().equals(Task.statusEnum.notDone)) {
                newTasks.add(task);
            }
        }
        if(newTasks.size() > 0) {
            return ResponseEntity.status(200).body(newTasks);
        } else {
            return ResponseEntity.status(400).body(new ResponseAPI("Bad Request - All tasks are completed!"));
        }
    }

}
