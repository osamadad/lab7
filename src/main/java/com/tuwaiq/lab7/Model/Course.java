package com.tuwaiq.lab7.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Course {
    @NotEmpty(message = "Sorry, your id can't be empty, please try again")
    @Size(min = 3, message = "Sorry, your id can't be less than 3 characters, please try again")
    private String id;
    @NotEmpty(message = "Sorry, your subject can't be empty, please try again")
    @Size(min = 4, message = "Sorry, your subject can't be less than 4 characters, please try again")
    private String subject;
    @NotEmpty(message = "Sorry, your teacher name can't be empty, please try again")
    @Pattern(regexp = "^[a-zA-Z]+$",message = "Sorry, your teacher name can't contain numbers, please try again")
    private String teacherName;
    @NotEmpty(message = "Sorry, your id can't be empty, please try again")
    @Size(min = 3, message = "Sorry, your id can't be less than 3 characters, please try again")
    private String teacherId;
    @NotEmpty(message = "Sorry, your id can't be empty, please try again")
    @Pattern(regexp = "Deep learning|Machine learning|Programming",message = "Sorry, your category must be either 'Deep learning', 'Machine learning', or 'Programming', please try again")
    private String category;
    @PositiveOrZero(message = "Sorry, your rating can only be positive or zero, please try again")
    @Max(value = 10, message = "Sorry, your rating can't exceed 10, please try again")
    private double rating;
}
