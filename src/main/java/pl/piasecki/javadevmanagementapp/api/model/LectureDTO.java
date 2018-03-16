package pl.piasecki.javadevmanagementapp.api.model;

import lombok.Data;
import pl.piasecki.javadevmanagementapp.domain.Student;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Data
public class LectureDTO {
    @NotNull
    @Size(min = 3, message = "Topic should be at least 3 characters long.")
    private String topic;

    @NotNull
    @Size(min = 3, message = "Localization should be at least 3 characters long.")
    private String localization;

    @Future
    private LocalDateTime date;

}
