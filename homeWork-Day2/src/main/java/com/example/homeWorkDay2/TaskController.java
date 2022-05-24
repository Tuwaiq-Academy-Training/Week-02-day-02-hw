package com.example.homeWorkDay2;

import org.springframework.web.bind.annotation.RestController;
import com.example.homeWorkDay2.ResponseApi;
import com.example.homeWorkDay2.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.ArrayList;

@RestController
public class TaskController {

    private ArrayList<Task> tasklist=new ArrayList();
    @GetMapping("/task")
    public ResponseEntity getTask(){
        return ResponseEntity.status(200).body(tasklist);
    }
    @PostMapping("/task")
    public ResponseEntity addTask(@RequestBody Task task){

        tasklist.add(task);
        return ResponseEntity.status(201).body(new ResponseApi("Task added successfully"));

    }
    @PutMapping("/task/{index}")
    public ResponseEntity updateTask(@PathVariable Integer index, @RequestBody Task task){
        if(index>tasklist.size()-1 || index<0){
            return ResponseEntity.status(400).body(new ResponseApi("you have entered an invalid index :"+index));
        }
        tasklist.set(index,task);
        return ResponseEntity.status(201).body(new ResponseApi("Task has been successfully updated "));


    }
    @DeleteMapping("/task/{index}")
    public ResponseEntity deletTask(@PathVariable Integer index){
        if(index>tasklist.size()-1 || index<0){
            return ResponseEntity.status(400).body(new ResponseApi("you have entered an invalid index :"+index));
        }
        tasklist.remove((int) index);
        return ResponseEntity.status(200).body(new ResponseApi("Task has been successfully deleted"));

    }
    @PutMapping("/task/{id}")
    public ResponseEntity changeStatus(@PathVariable Integer id,@RequestBody Boolean status){
        boolean isFound=false;
        for(int i=0;i<tasklist.size();i++){
            if(tasklist.get(i).getId().equals(id)){
                isFound=true;
                tasklist.get(i).setStatus(status);

            }
        }
        if(isFound==false){
            return ResponseEntity.status(400).body(new ResponseApi("Task not found"));
        }

        return ResponseEntity.status(201).body("Task status successfully changed");
    }
    @PutMapping("/task/title")
    public ResponseEntity searchTitle(@RequestBody String title){
        boolean isFound=false;
        int task_index=0;

        for(int i=0;i<tasklist.size();i++){
            if(tasklist.get(i).getTitle().toLowerCase().equals(title.toLowerCase())){
                isFound=true;
                task_index=i;

            }
        }
        if(isFound==false){
            return ResponseEntity.status(400).body(new ResponseApi("Task not found"));
        }

        return ResponseEntity.status(201).body(tasklist.get(task_index));
    }
    @PutMapping("/task/person/{id}")
    public ResponseEntity searchPerson(@PathVariable String id){
        boolean isFound=false;
        ArrayList<Task>personal_tasklist=new ArrayList<>();
        for(int i=0;i<tasklist.size();i++){
            if((tasklist.get(i).getPerson().getId()).equals(id)){
                isFound=true;
                personal_tasklist.add(tasklist.get(i));

            }
        }
        if(isFound==false){
            return ResponseEntity.status(400).body(new ResponseApi("No tasks found for the specified person"));
        }

        return ResponseEntity.status(201).body(personal_tasklist);
    }
    @GetMapping("/task/done")
    public ResponseEntity getDone(){

        int n_completed=0;
        ArrayList<Task>completed_tasklist=new ArrayList<>();
        for(int i=0;i<tasklist.size();i++){
            if(tasklist.get(i).isStatus()==true){
                n_completed++;
                completed_tasklist.add(tasklist.get(i));
            }
        }
        if(n_completed==0){
            return ResponseEntity.status(400).body(new ResponseApi("Not completed tasks"));
        }


        return ResponseEntity.status(200).body(completed_tasklist);


    }
}
