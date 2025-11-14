package com.tuwaiq.lab7.Controller;

import Api.ApiResponse;

import com.tuwaiq.lab7.Model.Homework;
import com.tuwaiq.lab7.Service.HomeworkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/homework-system")
@RequiredArgsConstructor
public class HomeworkController {


    private final HomeworkService homeworkService;

    @PostMapping("/add")
    public ResponseEntity<?> addHomework(@RequestBody @Valid Homework homework, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        homeworkService.addHomework(homework);
        return ResponseEntity.status(200).body(new ApiResponse("The homework have been added successfully"));
    }

    @GetMapping("/get")
    public ResponseEntity<?> getHomeworks() {
        ArrayList<Homework> homework = homeworkService.getHomeworks();
        if (homework.isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponse("There are no homeworks to show"));
        }
        return ResponseEntity.status(200).body(homework);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateHomework(@PathVariable String id, @Valid @RequestBody Homework homework, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        if (homeworkService.updateHomework(id, homework)) {
            return ResponseEntity.status(200).body(new ApiResponse("The course have been updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("No course with that id found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteHomework(@PathVariable String id) {
        if (homeworkService.deleteHomework(id)) {
            return ResponseEntity.status(200).body(new ApiResponse("The homework have been deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("No homework with that id found"));
    }

    @PutMapping("/grade-homework/{id}/{grade}")
    public ResponseEntity<?> gradeHomework(@PathVariable String id, @PathVariable double grade){
        if (homeworkService.gradeHomework(id,grade)){
            return ResponseEntity.status(200).body(new ApiResponse("The homework have been graded successfully"));
        }else {
            return ResponseEntity.status(400).body(new ApiResponse("No homework with that id found"));
        }
    }

    @PutMapping("/change-deadline/{id}/{newDeadline}")
    public ResponseEntity<?> changeDeadline(@PathVariable String id, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd@HH:mm:ss") LocalDateTime newDeadline){
        if (homeworkService.changeDeadline(id,newDeadline)){
            return ResponseEntity.status(200).body(new ApiResponse("The new deadline have been changed successfully"));
        }else {
            return ResponseEntity.status(400).body(new ApiResponse("No homework with that id found"));
        }
    }

    @GetMapping("/get/homework-by-course/{id}")
    public ResponseEntity<?> getHomeworksByCourse(@PathVariable String id){
        ArrayList<Homework> homeworksByCourse = homeworkService.getHomeworksByCourse(id);
        if (homeworksByCourse.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There are no homeworks in that course"));
        }
        else {
            return ResponseEntity.status(200).body(homeworksByCourse);
        }
    }

    @GetMapping("/get/late-submission")
    public ResponseEntity<?> getLateSubmission(){
        ArrayList<Homework> lateSubmission = homeworkService.getLateSubmission();
        if (lateSubmission.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There are no late submissions for this homework"));
        }
        else {
            return ResponseEntity.status(200).body(lateSubmission);
        }
    }


}
