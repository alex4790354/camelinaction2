package camelinaction.ch03_converter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

    private String teacherName;
    private int teacherId;
    private int salary;

}
