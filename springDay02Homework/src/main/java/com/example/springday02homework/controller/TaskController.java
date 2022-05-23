package com.example.springday02homework.controller;

import com.example.springday02homework.model.Person;
import com.example.springday02homework.model.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
public class TaskController {

    ArrayList<Task> tasks = new ArrayList<Task>();

    @GetMapping("task")
    public ResponseEntity getTask(){
        return ResponseEntity.status(200).body(tasks);
    }

    @PostMapping("task")
    public ResponseEntity postTask(@RequestBody Task task){
        tasks.add(task);
        return ResponseEntity.status(201).body(tasks);
    }

    @PutMapping("task/{id}")
    public ResponseEntity updateTask(@RequestBody Task task , @PathVariable String id ){
        Integer index=getIndex(id);
        if(index==0){
            return ResponseEntity.status(400).body(new ResponseAPI("there is no task"));
        }
        tasks.set(index,task);
        return ResponseEntity.status(200).body(new ResponseAPI(task.getTitle()+" has updated"));
    }

    @DeleteMapping("task/{id}")
    public ResponseEntity deleteTask(@PathVariable String id){
        Integer index=getIndex(id);
        if(index==null){
            return ResponseEntity.status(400).body(new ResponseAPI("there is no task"));
        }
        tasks.remove((int)index);
        return ResponseEntity.status(200).body(new ResponseAPI("task has deleted"));
    }

    @PutMapping("task/status/{id}")
    public ResponseEntity updateTaskStatus(@RequestBody String status,@PathVariable String id ){
        System.out.println(status);
        Integer index=getIndex(id);
        System.out.println(status);
        if(index==null ||!status.equalsIgnoreCase("done")&&!status.equalsIgnoreCase("notDone")){
            return ResponseEntity.status(400).body(new ResponseAPI("Bad request"));
        }
        System.out.println(status);
        tasks.get(index).setStatus(status);
        return ResponseEntity.status(200).body(new ResponseAPI("task has updated"));
    }

    @GetMapping("task/{title}")
    public ResponseEntity getTaskByTitle(@PathVariable String title){
        Integer index=getIndexByTitle(title);
        if (index==null){
            return ResponseEntity.status(400).body(new ResponseAPI("Bad request"));
        }
        return ResponseEntity.status(200).body(tasks.get(index));
    }

    @GetMapping("task/person/{id}")
    public ResponseEntity getTaskByPersonId(@PathVariable String id){
        Integer index=getIndexByPerson(id);
        if (index==null){
            return ResponseEntity.status(400).body(new ResponseAPI("Bad request"));
        }
        return ResponseEntity.status(200).body(tasks.get(index));
    }

    @GetMapping("task/status/notDone")
    public ResponseEntity getAllNotDoneTask(){
        ArrayList<Task>allNotDone=getNotDoneTask();
        if (allNotDone.size()==0){
            return ResponseEntity.status(400).body(new ResponseAPI("Bad request"));
        }
        return ResponseEntity.status(200).body(allNotDone);
    }

    public Integer getIndex(String id){
        for (int i = 0; i < this.tasks.size(); i++) {
            if (tasks.get(i).getId().equals(id)){
                return i;
            }
        }
        return null;
    }

    public Integer getIndexByTitle(String title){
        for (int i = 0; i < this.tasks.size(); i++) {
            if (tasks.get(i).getTitle().equalsIgnoreCase(title)){
                return i;
            }
        }
        return null;
    }

    public Integer getIndexByPerson(String id){
        for (int i = 0; i < this.tasks.size(); i++) {
            if (tasks.get(i).getPerson().getId().equalsIgnoreCase(id)){
                return i;
            }
        }
        return null;
    }

    public ArrayList<Task> getNotDoneTask(){
        ArrayList<Task> notDoneTask=new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getStatus().equalsIgnoreCase("notDone")){
                notDoneTask.add(tasks.get(i));
            }
        }
        return notDoneTask;
    }
}
