package com.example.firstspringboot.comntroller;

import com.example.firstspringboot.Person;
import com.example.firstspringboot.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/task")
public class TaskController {
   // LocalDate date1 = LocalDate.of(2022, 03, 28);
   List<Task> tasks = new ArrayList<>();

    TaskController() throws ParseException {


        LocalDate date1 = LocalDate.of(2022, 03, 28);
        Integer integer = 1;
        Person person1 = new Person("0", "Ahmed", integer);
        Person person2 = new Person("1", "Ahmed", integer);
        Person person3 = new Person("2", "Ahmed", integer);
        Person person4 = new Person("4", "Ahmed", integer);

        tasks.addAll(
                List.of(
                        new Task("0","Fish", "do the fishes",false, date1, person1),
                        new Task("1","Egg", "do the fishes",false, date1, person2),
                        new Task("2","Pulop", "do the fishes",false, date1, person3),
                        new Task("3","Deer", "do the fishes",false, date1, person4)
                ));
    }

    @GetMapping()
    ResponseEntity<Object> getTask(){
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<Object> postNewTask(@RequestBody Task t) {
        tasks.add(t);
        return new ResponseEntity<>(t, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putChangeStatus(@PathVariable int id){
        int checkForWork = -1;

        String stringID = String.valueOf(id);
        Task task = getById(tasks, stringID);
        if (task != null){
            checkForWork = Integer.parseInt(task.getId());
            task.setStatus(!task.isStatus());
        }
        return (checkForWork == -1) ? new ResponseEntity<>("Not Found, no task with that id", HttpStatus.BAD_REQUEST)
                :   new ResponseEntity<>(task, HttpStatus.OK);

    }

    @GetMapping ("/{title}")
    public ResponseEntity<Object> getTaskByTitle(@PathVariable String title){
        int checkForWork = -1;
        Task task = getByTitle(tasks, title);
        if (task != null) {
            checkForWork = Integer.parseInt(task.getId());
        }
        return (checkForWork == -1) ? new ResponseEntity<>("Not Found, no task with that id", HttpStatus.BAD_REQUEST)
                :   new ResponseEntity<>(task, HttpStatus.OK);

    }

    /// needs work
    @GetMapping ("/{id}")
    public ResponseEntity<Object> getTaskByPersonID(@PathVariable int id){
        int checkForWork = -1;
        String stringID = String.valueOf(id);
        Task task = getByPersonID(tasks, stringID);
        if (task != null){
            checkForWork = id;
        }
        return (checkForWork == -1) ? new ResponseEntity<>("Not Found, no task with that id", HttpStatus.BAD_REQUEST)
                :   new ResponseEntity<>(task, HttpStatus.OK);
    }

    private Task getByPersonID(List<Task> t, String stringID) {
        for (Task task: tasks){
            if (task.getPerson().getId().equals(stringID)){
                return task;
            }
        }
        return null;
    }

    @GetMapping ("/unfinished")
    public ResponseEntity<Object> putChangeStatus(){
        int checkForWork = -1;
        List<Task> unDoneTasks = new ArrayList<>();
        for (Task task: tasks){
            if (!task.isStatus()){
                unDoneTasks.add(task);
                checkForWork++;
            }
        }
        return (checkForWork == -1) ? new ResponseEntity<>("No unfinished done task", HttpStatus.BAD_REQUEST)
                :   new ResponseEntity<>(unDoneTasks, HttpStatus.OK);
    }

    public static Task getById(List<Task> t, String id){
        for (Task task: t) {
            if (task.getId().equals(id)){
                return task;
            }
        }
        return null;
    }

    public static Task getByTitle(List<Task> t, String title){
        for (Task task: t) {
            if (task.getTitle().toLowerCase(Locale.ROOT).equals(title.toLowerCase(Locale.ROOT))){
                return task;
            }
        }
        return null;
    }
}
