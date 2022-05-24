package com.example.h02w02.MainControllers;


import com.example.h02w02.models.Task;
import com.sun.source.tree.BreakTree;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TaskController {

    List<Task> tasks = new ArrayList<>();

    @GetMapping("task")
    public ResponseEntity getTasks(){
        return ResponseEntity.status(200).body(tasks);
    }

    @PostMapping("task")
    public ResponseEntity addTask(@RequestBody Task t){
        tasks.add(t);
        return ResponseEntity.status(201).body(t.getTitle() + " has been added");
    }

    @PutMapping("task/{index}")
    public ResponseEntity changeState(@PathVariable int index){
        if(index > tasks.size()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid index");
        }
        else
            if(tasks.get(index).isDone()){
                tasks.get(index).setDone(false);
            }
            else
                tasks.get(index).setDone(true);
        return ResponseEntity.status(HttpStatus.OK).body("Status has been changed");
    }

    @GetMapping("task/search/title")
    public ResponseEntity searchByTitle(@RequestParam String title){
        for (Task t: tasks){
            if (t.getTitle().equals(title)){
                return ResponseEntity.status(200).body(title + " task has been found");
            }
        }
        return ResponseEntity.status(400).body(title + " task has not been found");
    }
    @GetMapping("task/search/person")
    public ResponseEntity searchById(@RequestParam int id){
        for (Task t: tasks){
            if (t.getPerson().getID().equals(id)){
                return ResponseEntity.status(200).body(id + " task has been found");
            }
        }
        return ResponseEntity.status(400).body(id + " task has not been found");
    }
    @GetMapping("task/done")
    public ResponseEntity getDoneTasks(){
        List<Task> notDone = new ArrayList<>();
        for(Task t: tasks){
            if(!t.isDone()){
                notDone.add(t);
            }
        }
        if (notDone.size() == 0){
            return ResponseEntity.status(400).body("All tasks are done");
        }
        else
            return ResponseEntity.status(200).body(notDone);
    }
}
