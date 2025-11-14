package com.tuwaiq.lab7.Service;

import com.tuwaiq.lab7.Model.Homework;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class HomeworkService {
    ArrayList<Homework> homeworks = new ArrayList<>();

    public ArrayList<Homework> getHomeworks() {
        return homeworks;
    }

    public void addHomework(Homework homework) {
        homework.setSubmissionDate(LocalDateTime.now());
        homeworks.add(homework);
    }

    public Boolean updateHomework(String id, Homework Homework) {
        for (int i = 0; i < homeworks.size(); i++) {
            if (homeworks.get(i).getId().equalsIgnoreCase(id)) {
                homeworks.set(i, Homework);
                return true;
            }
        }
        return false;
    }

    public Boolean deleteHomework(String id) {
        for (int i = 0; i < homeworks.size(); i++) {
            if (homeworks.get(i).getId().equalsIgnoreCase(id)) {
                homeworks.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean changeDeadline(String id, LocalDateTime newDeadline){
        for (Homework homework:homeworks){
            if (homework.getId().equalsIgnoreCase(id)){
                homework.setDeadline(newDeadline);
                return true;
            }
        }
        return false;
    }

    public boolean gradeHomework(String id,double grade){
        for (Homework homework:homeworks){
            if (homework.getId().equalsIgnoreCase(id)){
                homework.setGrade(grade);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Homework> getHomeworksByCourse(String id){
        ArrayList<Homework> homeworksByCourse = new ArrayList<>();
        for (Homework homework:homeworks){
            if (homework.getCourseId().equalsIgnoreCase(id)){
                homeworksByCourse.add(homework);
            }
        }
        return homeworksByCourse;
    }

    public ArrayList<Homework> getLateSubmission(){
        ArrayList<Homework> lateSubmissions = new ArrayList<>();
        for (Homework homework:homeworks){
            if (homework.getSubmissionDate().isAfter(homework.getDeadline())){
                lateSubmissions.add(homework);
            }
        }
        return lateSubmissions;
    }
}
