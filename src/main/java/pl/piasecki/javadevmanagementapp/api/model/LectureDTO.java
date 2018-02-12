package pl.piasecki.javadevmanagementapp.api.model;

import lombok.Data;

import java.util.Date;

@Data
public class LectureDTO {
    private String topic;
    private String localization;
    private Date date;
}
