package pl.piasecki.javadevmanagementapp.api.model;

import lombok.Data;
import pl.piasecki.javadevmanagementapp.domain.Lecture;

import java.util.Set;

@Data
public class StudentDTO {
    private String firstName;
    private String lastName;
    private String email;
}
