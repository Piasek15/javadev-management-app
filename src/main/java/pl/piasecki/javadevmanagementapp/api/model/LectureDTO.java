package pl.piasecki.javadevmanagementapp.api.model;

import lombok.Data;
import pl.piasecki.javadevmanagementapp.domain.Student;

import java.util.Date;
import java.util.Set;

@Data
public class LectureDTO {
    private String topic;
    private String localization;
    private Date date;

}
