package pl.piasecki.javadevmanagementapp.api.model;

import lombok.Data;

@Data
public class LSLectureAndGradeDTO {
    private LectureDTO lecture;
    private Double grade;
}
