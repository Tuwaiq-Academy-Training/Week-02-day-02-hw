package com.example.hw22.TaskTrackerSystem;

import com.example.hw22.BankManagement.CustomersModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
public class ControllerSystem {
    private ArrayList<Task> tasks = new ArrayList<>();
    public LocalDate deadline(){
        return LocalDate.now();
    }
    @GetMapping("task")
    public ResponseEntity<ArrayList<Task>> getTasks(){
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }
    @PostMapping("task")
    public ResponseEntity<ApiSystem> createTask(@RequestBody Task task){
        tasks.add(task);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiSystem("Task: "+task.getTitle()+"  added",200));
    }
    @PutMapping("task/{index}")
    public ResponseEntity<ApiSystem> changeTask(@PathVariable Integer index,@RequestBody Task status){
      if (status.getStatus().equals("notDone")){
          tasks.set(index,status);
          return ResponseEntity.status(HttpStatus.OK).body(new ApiSystem("Task: "+status.getStatus()+"  Edit",200));
      } else {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiSystem("Sorry Your request is bad!",400));
      }
    }
    @GetMapping("task/{status}")
    public ResponseEntity<ArrayList<Task>> taskTitle(@RequestBody Task status){
       if (status.getStatus().length() > 5){
           return ResponseEntity.status(HttpStatus.OK).body(tasks);
       }else {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(tasks);
       }
    }
    @GetMapping("task/{status}/{notDone}")
    public ResponseEntity<ArrayList<Task>> taskTitleNotDone(@RequestBody Task status){
        if (status.getStatus().equals("notDone")){
            return ResponseEntity.status(HttpStatus.OK).body(tasks);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(tasks);
        }
    }
    @GetMapping("task/{index}/person")
    public ResponseEntity<ArrayList<Task>> taskPerson(@PathVariable Integer index,@RequestBody Task request){
       try {
           return ResponseEntity.status(HttpStatus.OK).body(tasks);
       } catch (Exception e){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(tasks);
       }
    }
}
