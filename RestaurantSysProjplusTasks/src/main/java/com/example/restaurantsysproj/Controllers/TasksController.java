package com.example.restaurantsysproj.Controllers;


import com.example.restaurantsysproj.models.TaskClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class TasksController {



    ArrayList<String> Tasks = new ArrayList<>();


    // display tasks
    @GetMapping("Task")
    public ResponseEntity<ArrayList<String>> getTask(){
        return ResponseEntity.status(HttpStatus.OK).body(Tasks);

    }

   // create new tasks
    @PostMapping("Task")
    public ResponseEntity creatTask(@RequestBody String newTask){
        Tasks.add(newTask);
        return ResponseEntity.status(HttpStatus.CREATED).body("new Task " + newTask + "added");

    }
  // check status of the task
    @PutMapping("Task/{index}")
    public ResponseEntity checkStatus(@RequestBody TaskClass taskClass , Integer index){

        if(taskClass.isDone()){
            return ResponseEntity.status(HttpStatus.OK).body("the task " + taskClass.getTitle() + "Done");
        } if(taskClass.isNotDone()){
            return ResponseEntity.status(HttpStatus.OK).body("the task " + taskClass.getTitle() + "NotDone");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no task to check !!");



    }








}
