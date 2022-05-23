package com.example.springtask.controllers;

import com.example.springtask.model.ResponseApi;
import com.example.springtask.model.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
public class TaskController {

    private ArrayList<Task> tasks = new ArrayList<>();

    @GetMapping("task")
    public ResponseEntity getTask() {
        return ResponseEntity.status(200).body(tasks);
    }

    @GetMapping("task/done")
    public ResponseEntity getDone() {

        int c = 0;

        ArrayList<Task> c_tasks = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).isStatus() == true) {
                c++;
                c_tasks.add(tasks.get(i));
            }
        }
        if (c == 0) {
            return ResponseEntity.status(400).body(new ResponseApi("no tasks"));
        }
        return ResponseEntity.status(400).body(c);
    }

    @PostMapping("task")
    public ResponseEntity addTask(@RequestBody Task task) {
        tasks.add(task);
        return ResponseEntity.status(201).body(new ResponseApi("add"));
    }

    @PutMapping("task/{index}")
    public ResponseEntity updateTask(@PathVariable Integer index, @RequestBody Task task) {
        if (index > tasks.size() - 1 || index < 0) {
            return ResponseEntity.status(400).body(new ResponseApi("Enter an invalid index :" + index));
        }
        return ResponseEntity.status(201).body(new ResponseApi("Updated"));
    }

    @PutMapping("task/{index}/id")
    public ResponseEntity taskStatus(@PathVariable Integer id, @RequestBody Boolean status){
        boolean isF = false;
        for(int i = 0; i < tasks.size(); i++){
            if(tasks.get(i).getId().equals(id)){
                isF = true;
                tasks.get(i).setStatus(status);
            }
        }
        if (isF == false){
            return ResponseEntity.status(400).body(new ResponseApi("no done"));
        }
        return ResponseEntity.status(201).body(new ResponseApi("change"));
    }

    @PutMapping("task/{index}/title")
    public ResponseEntity SearchTitle(@RequestBody String title){
        boolean found = false;
        int index =0;

        for(int i = 0; i < tasks.size(); i++){
            if(tasks.get(i).getTitle().toLowerCase().equals(title.toLowerCase())){
                found = true;
                index = i;

            }
        }
        if(found == false){
            return ResponseEntity.status(400).body(new ResponseApi("Task not found"));
        }

        return ResponseEntity.status(201).body(tasks.get(index));
    }

    @PutMapping("/task/id/person")
    public ResponseEntity searchPerson(@PathVariable String id){
        boolean found = false;
        ArrayList<Task>personal_tasks=new ArrayList<>();
        for(int i=0;i<tasks.size();i++){
            if((tasks.get(i).getPerson().getId()).equals(id)){
                found=true;
                personal_tasks.add(tasks.get(i));

            }
        }
        if(found==false){
            return ResponseEntity.status(400).body(new ResponseApi("No  found for the specified person"));
        }

        return ResponseEntity.status(201).body(personal_tasks);
    }

    @DeleteMapping("/task/{index}")
    public ResponseEntity deletTask(@PathVariable Integer index){
        if(index>tasks.size()-1 || index<0){
            return ResponseEntity.status(400).body(new ResponseApi("Entered an invalid index :"+index));
        }
        tasks.remove((int) index);
        return ResponseEntity.status(200).body(new ResponseApi("Task deleted"));
    }
}