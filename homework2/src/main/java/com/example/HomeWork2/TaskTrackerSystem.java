package com.example.HomeWork2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TaskTrackerSystem {



    private ArrayList<Task> tasks = new ArrayList<>();

    @GetMapping ("task")
    public ResponseEntity getTasks(){
        return ResponseEntity.status(200).body(tasks);
    }

    @PostMapping("task")
    public ResponseEntity addTask(@RequestBody Task task){
        tasks.add(task);
        return ResponseEntity.status(201).body(task);
    }


    @PutMapping("task/{id}")
    public ResponseEntity updateTask(@PathVariable int id, @RequestBody Task task){
        if( id > tasks.size()-1  ){
            return ResponseEntity.status(400).body("task is invalid");
        }
        String s = task.getStatus();
        tasks.get(id).setStatus(s);
        return ResponseEntity.status(200).body( " Task " +tasks.get(id).getTitle() + " is Update");
    }



    @DeleteMapping("task/{id}")
    public ResponseEntity deleteTask( @PathVariable int id){
        if ( id > tasks.size() - 1 ) {
            return ResponseEntity.status(200).body("Index is not valid " + id);
        }
        tasks.remove(id);
        return ResponseEntity.status(200).body(" Task with index " + id + " is deleted");
    }

    @GetMapping("task/searchTitle")
    public ResponseEntity searchByTitle(@RequestParam(name = "title") String title)
    {
        List<Task> found = tasks.stream().filter(x->x.getTitle().contains(title)).collect(Collectors.toList());
        if(found== null || found.size() <= 0){
            return ResponseEntity.status(400).body("no tasks with title " + title);
        }
        return ResponseEntity.status(200).body(found);
    }
    @GetMapping("task/searchPerson")
    public ResponseEntity searchByPerson(@RequestParam(name = "person") String person)
    {
        List<Task> found = tasks.stream().filter(x->x.getPerson() != null && x.getPerson().getID().contains(person)).collect(Collectors.toList());
        if(found== null || found.size() <= 0){
            return ResponseEntity.status(400).body("no tasks with person id " + person);
        }
        return ResponseEntity.status(200).body(found);
    }

    @GetMapping("task/notDone")
    public ResponseEntity allNotDone()
    {
        List<Task> found = tasks.stream().filter(x->x.getStatus().equals("notDone")).collect(Collectors.toList());
        if(found== null || found.size() <= 0){
            return ResponseEntity.status(400).body("no tasks with status not done");
        }
        return ResponseEntity.status(200).body(found);
    }
}
