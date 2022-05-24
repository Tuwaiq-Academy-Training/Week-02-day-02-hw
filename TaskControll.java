package controller;


import com.example.springhw2.ResponsApi;
import com.example.springhw2.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class TaskControll {

    public ArrayList<Task> tasks = new ArrayList();

    @GetMapping("task") //display task
    public ResponseEntity<ArrayList<Task>> getTask() {
        return ResponseEntity.status(200).body(tasks);
    }

    @PostMapping("task") // creat task
    public ResponseEntity<ResponsApi> addTask(@RequestBody Task task) {
        tasks.add(task);
        return ResponseEntity.status(201).body(new ResponsApi(" Task added "));
    }

    @PutMapping("statue/{id}")//change task status
    public ResponseEntity<ResponsApi> taskStatue(@PathVariable String id) {
        for (Task task : tasks) {//for(data_type variable : array)
            if (task.getId() == id) {
                if (task.getStatus().equals("done")) {
                    task.setStatus("notDone");
                    return ResponseEntity.status(201).body(new ResponsApi(" Task is notDone "));
                } else {
                    task.setStatus("done");
                }
                return ResponseEntity.status(201).body(new ResponsApi("Task is Done"));
            }
        }
        return ResponseEntity.status(400).body(new ResponsApi("ID not found"));
    }

    @GetMapping("taskTitle/{title}") //search tasks by title
    public ResponseEntity getTaskByT(@PathVariable String title) {
        ArrayList taskTitle=new ArrayList<>();
        for(Task task :tasks){
            if(task.getTitle()==title){
                taskTitle.add(task);
                return ResponseEntity.status(201).body(taskTitle);
            }
        }
        return ResponseEntity.status(400).body("Task not found");
    }

    @GetMapping("personId/{id}") //search tasks by person id
    public ResponseEntity getTaskByP(@PathVariable String id) {
        ArrayList personTask=new ArrayList<>();
        for(Task task :tasks){
            if(task.getPerson().getId()==id){
                personTask.add(task);
                return ResponseEntity.status(201).body(personTask);
            }
        }
        return ResponseEntity.status(400).body("Person Id not found");
    }

    @GetMapping("notdone")
    public ResponseEntity getNotDoneTask(){
        ArrayList notDone = new ArrayList();
        for (Task task :tasks){
            if(task.getStatus().equals("notDone")){
                notDone.add(task);
                return ResponseEntity.status(201).body(notDone);
            }
        }
        return ResponseEntity.status(400).body("All tasks are done");
    }
}