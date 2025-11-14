package com.tuwaiq.lab7.Service;

import com.tuwaiq.lab7.Model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseService {

    ArrayList<Course> courses = new ArrayList<>();

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course Course) {
        courses.add(Course);
    }

    public Boolean updateCourse(String id, Course Course) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equalsIgnoreCase(id)) {
                courses.set(i, Course);
                return true;
            }
        }
        return false;
    }

    public Boolean deleteCourse(String id) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equalsIgnoreCase(id)) {
                courses.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Course> getCourseByTeacherName(String name) {
        ArrayList<Course> coursesByName = new ArrayList<>();
        for (Course course : courses) {
            if (course.getTeacherName().equalsIgnoreCase(name)) {
                coursesByName.add(course);
            }
        }
        return coursesByName;
    }

    public ArrayList<Course> getCourseByCategory(String category) {
        ArrayList<Course> CoursesByCategory = new ArrayList<>();
        for (Course course : courses) {
            if (course.getCategory().equalsIgnoreCase(category)) {
                CoursesByCategory.add(course);
            }
        }
        return CoursesByCategory;
    }

    public ArrayList<Course> getCourseWithHigherRating(double rating) {
        ArrayList<Course> CoursesWithHigherRating = new ArrayList<>();
        for (Course course : courses) {
            if (course.getRating()>rating) {
                CoursesWithHigherRating.add(course);
            }
        }
        return CoursesWithHigherRating;
    }

    public Boolean rateCourse(String id, double rating){
        for (Course course:courses){
            if (course.getId().equalsIgnoreCase(id)){
                course.setRating(rating);
                return true;
            }
        }
        return false;
    }
}
