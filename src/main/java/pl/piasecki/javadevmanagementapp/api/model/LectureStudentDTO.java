package pl.piasecki.javadevmanagementapp.api.model;

import lombok.Data;

@Data
public class LectureStudentDTO {
    private LectureDTO lecture;
    private StudentDTO student;
    private Double grade;
}
