package com.example.homework6.Controller;

import com.example.homework6.Model.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class TaskController {

    private ArrayList<Task> tasks = new ArrayList<>();

    @GetMapping("task")
    public ResponseEntity getTasks(){
        return ResponseEntity.status(201).body(tasks);
    }

    @PostMapping("task")
    public ResponseEntity addTasks(@RequestBody Task task){
        tasks.add(task);
        return ResponseEntity.status(201).body("Add task into array");
    }

    @PutMapping("task/{index}")
    public ResponseEntity changeTask(@PathVariable int index, @RequestBody String status){
        if (index < tasks.size()-1){
            return ResponseEntity.status(401).body("Bad request invalid index");
        }
        if (status.equals("done") || status.equals("notDone")){
            Task task = tasks.get(index);
            if (status.equals("done")){
                task.setStatus(Task.statusE.done);
            }else {
                task.setStatus(Task.statusE.notDone);
            }
            return ResponseEntity.status(201).body("Change the status");
        }else {
            return ResponseEntity.status(401).body("Bad request invalid input add done or noDone");
        }
    }

    @GetMapping("task/{title}")
    public ResponseEntity getTitle(@PathVariable String title){
        Task titleTask = null;
        for (Task task : tasks){
            if (task.getTitle().equals(title)){
                titleTask = task;
            }
        }
        if (titleTask == null){
            return ResponseEntity.status(401).body("Bad request title is: "+title );
        }else {
            return ResponseEntity.status(201).body(titleTask);
        }
    }

    @GetMapping("task/person/{id}")
    public ResponseEntity getTaskId(@PathVariable String id){
        Task idTask = null;
        for (Task task : tasks){
            if (task.getPerson().getId().equals(id)){
                idTask = task;
            }
        }
        if(idTask == null){
            return ResponseEntity.status(401).body("Bad request title is: "+id );
        }else {
            return ResponseEntity.status(201).body(idTask);
        }
    }

    @GetMapping("noDone")
    public ResponseEntity getNoDone(){
        if (tasks.size() < 0){
            return ResponseEntity.status(400).body("Bad request array is empty");
        }
        ArrayList<Task> noDoneList = new ArrayList<>();
        for (Task task : tasks){
            if(task.getStatus().equals(Task.statusE.notDone)){
                noDoneList.add(task);
            }
        }
        if(noDoneList.size() < 0){
            return ResponseEntity.status(400).body("Bad request");
        }else {
            return ResponseEntity.status(201).body(noDoneList);
        }
    }

}
