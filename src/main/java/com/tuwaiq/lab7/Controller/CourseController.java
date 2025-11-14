package com.tuwaiq.lab7.Controller;

import Api.ApiResponse;
import com.tuwaiq.lab7.Model.Course;
import com.tuwaiq.lab7.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/course-system")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/add")
    public ResponseEntity<?> addCourse(@RequestBody @Valid Course course, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        courseService.addCourse(course);
        return ResponseEntity.status(200).body(new ApiResponse("The course have been added successfully"));
    }

    @GetMapping("/get")
    public ResponseEntity<?> getCourses() {
        ArrayList<Course> courses = courseService.getCourses();
        if (courses.isEmpty()) {
            return ResponseEntity.status(400).body(new ApiResponse("There are no courses to show"));
        }
        return ResponseEntity.status(200).body(courses);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable String id, @Valid @RequestBody Course course, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        if (courseService.updateCourse(id, course)) {
            return ResponseEntity.status(200).body(new ApiResponse("The course have been updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("No course with that id found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable String id) {
        if (courseService.deleteCourse(id)) {
            return ResponseEntity.status(200).body(new ApiResponse("The course have been deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("No course with that id found"));
    }

    @GetMapping("/get/by-teacher-name/{name}")
    public ResponseEntity<?> getCourseByTeacherName(@PathVariable String name){
        ArrayList<Course> coursesByTeacherName= courseService.getCourseByTeacherName(name);
        if (coursesByTeacherName.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There are no courses with that teacher name"));
        }
        else {
            return ResponseEntity.status(200).body(coursesByTeacherName);
        }
    }

    @GetMapping("/get/by-category/{category}")
    public ResponseEntity<?> getCourseByCategory(@PathVariable String category){
        ArrayList<Course> coursesByCategory = courseService.getCourseByCategory(category);
        if (coursesByCategory.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There are no courses in this category"));
        }
        else {
            return ResponseEntity.status(200).body(coursesByCategory);
        }
    }

    @GetMapping("/get/with-higher-rating/{rating}")
    public ResponseEntity<?> getCourseWithHigherRating(@PathVariable double rating){
        ArrayList<Course> coursesWithHigherRating = courseService.getCourseWithHigherRating(rating);
        if (coursesWithHigherRating.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("There are no courses with higher rating"));
        }
        else {
            return ResponseEntity.status(200).body(coursesWithHigherRating);
        }
    }

    @PutMapping("/rate-course/{id}/{rating}")
    public ResponseEntity<?> rateCourse(@PathVariable String id, @PathVariable double rating){
        if (courseService.rateCourse(id,rating)){
            return ResponseEntity.status(200).body(new ApiResponse("The course have been rating successfully"));
        }else {
            return ResponseEntity.status(400).body(new ApiResponse("No course with that id found"));
        }
    }
}
