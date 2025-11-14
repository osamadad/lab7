package com.tuwaiq.lab7.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Homework {
    @NotEmpty(message = "Sorry, your id can't be empty, please try again")
    @Size(min = 3, message = "Sorry, your id can't be less than 3 characters, please try again")
    private String id;
    @NotEmpty(message = "Sorry, your course id can't be empty, please try again")
    @Size(min = 3, message = "Sorry, your course id can't be less than 3 characters, please try again")
    private String courseId;
    @NotEmpty(message = "Sorry, your student id can't be empty, please try again")
    @Size(min = 3, message = "Sorry, your student id can't be less than 3 characters, please try again")
    private String studentId;
    @NotEmpty(message = "Sorry, your type can't be empty, please try again")
    @Pattern(regexp = "Project|Quiz|Homework|Essay",message = "Sorry, your category must be either 'Project', 'Quiz', 'Homework' or 'Essay', please try again")
    private String type;
    @PositiveOrZero(message = "Sorry, your grade can only be positive or zero, please try again")
    @Max(value = 100, message = "Sorry, your grade can't exceed 100, please try again")
    private double grade;
    @NotNull(message = "Sorry, your deadline can't be empty, please try again")
    @Future(message = "Sorry, your deadline can only be in the future, please try again")
    private LocalDateTime deadline;
    private LocalDateTime submissionDate;
}
