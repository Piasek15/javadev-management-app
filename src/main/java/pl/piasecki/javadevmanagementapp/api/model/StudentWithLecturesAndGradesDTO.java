package pl.piasecki.javadevmanagementapp.api.model;

import lombok.Data;

import java.util.Set;

@Data
public class StudentWithLecturesAndGradesDTO {
    private String firstName;
    private String lastName;
    private String email;
    private Set<LSLectureAndGradeDTO> lecturesAndGrades;
}
