package pl.piasecki.javadevmanagementapp.api.model;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class LectureStudentDTO {
    private LectureDTO lecture;
    private StudentDTO student;

    @Min(value = 1)
    @Max(value = 6)
    private Double grade;
}
