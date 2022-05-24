package com.example.atheerhw2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class TaskTracker {
    private ArrayList<Task> tasks = new ArrayList<>();

    @GetMapping("task")
    public ResponseEntity<ArrayList<Task>> getTask() {
        return ResponseEntity.status(200).body(tasks);
    }

    @PostMapping("task")
    public ResponseEntity<ResponseAPI> addTask(@RequestBody Task task) {
        tasks.add(task);
        return ResponseEntity.status(200).body(new ResponseAPI("Task " + task.getID() + " added"));

    }

    @PutMapping("task/change/{index}")
    public ResponseEntity<ResponseAPI> ChangeStatus (@PathVariable String index) {
        Integer number = Integer.parseInt(index);

        if (number>tasks.size() - 1) {

            return ResponseEntity.status(400).body(new ResponseAPI("The index " + index
                    + " is more than array length " + tasks.size()));
        }

        for (int i = 0; i < tasks.size(); i++) {
            if ((tasks.get(i).getID()).equals( index)) {
                if ((tasks.get(i).getStatus()).equals("Done"))
                    tasks.get(i).setStatus(Task.Status.valueOf("notDone"));
                else
                    tasks.get(i).setStatus(Task.Status.valueOf("Done"));

            }


        }

        return ResponseEntity.status(200).body(new ResponseAPI("Task Change Status "));


    }

    @GetMapping("task/title/{index}")
    public ResponseEntity<ResponseAPI> SearchByTitle (@PathVariable String index) {

        String title;
        for (int i = 0; i < tasks.size(); i++) {
            if ((tasks.get(i).getTitle()).equals( index)) {
                 title= "Task{" +
                            "ID='" + tasks.get(i).getID() + '\'' +
                            ", title='" + tasks.get(i).getTitle() + '\'' +
                            ", description='" + tasks.get(i).getDescription() + '\'' +
                            ", deadline=" + tasks.get(i).getDeadline() +
                            ", person=" + tasks.get(i).getPerson() +
                            ", status=" + tasks.get(i).getStatus() +
                            '}';
                return ResponseEntity.status(200).body(new ResponseAPI("Task title is " + title ));

            }
        }
            return ResponseEntity.status(400).body(new ResponseAPI("The title " + index + " is none " ));

        }

    @GetMapping("task/person/{index}")
    public ResponseEntity<ResponseAPI> SearchByPerson (@PathVariable String index) {

        String pID;
        for (int i = 0; i < tasks.size(); i++) {
            if ((tasks.get(i).getPerson().getID()).equals( index)) {
                pID= "Task{" +
                        "ID='" + tasks.get(i).getID() + '\'' +
                        ", title='" + tasks.get(i).getTitle() + '\'' +
                        ", description='" + tasks.get(i).getDescription() + '\'' +
                        ", deadline=" + tasks.get(i).getDeadline() +
                        ", person=" + tasks.get(i).getPerson() +
                        ", status=" + tasks.get(i).getStatus() +
                        '}';
                return ResponseEntity.status(200).body(new ResponseAPI("Task title is " + pID ));

            }
        }
        return ResponseEntity.status(400).body(new ResponseAPI("The title " + index + " is none " ));

    }

    @GetMapping("task/change/{index}")
    public ResponseEntity<ResponseAPI> getStatus () {

        String ND=null;
        for (int i = 0; i < tasks.size(); i++) {

                if ((tasks.get(i).getStatus()).equals("notDone")){
                    ND+= "Task{" +
                            "ID='" + tasks.get(i).getID() + '\'' +
                            ", title='" + tasks.get(i).getTitle() + '\'' +
                            ", description='" + tasks.get(i).getDescription() + '\'' +
                            ", deadline=" + tasks.get(i).getDeadline() +
                            ", person=" + tasks.get(i).getPerson() +
                            ", status=" + tasks.get(i).getStatus() +
                            '}';}


//                    tasks.get(i).setStatus(Task.Status.valueOf("notDone"));



        }
        if (ND.equalsIgnoreCase(null))
            return ResponseEntity.status(200).body(new ResponseAPI("All task Status notDone "+ ND));
        else
            return ResponseEntity.status(400).body(new ResponseAPI("All task is done")); }








}




