package pl.piasecki.javadevmanagementapp.api.model;

import lombok.Data;
import pl.piasecki.javadevmanagementapp.domain.Lecture;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class StudentDTO {
    @NotNull
    @Size(min = 2, message = "First name should be at least 2 characters long")
    private String firstName;

    @NotNull
    @Size(min = 2, message = "Last name should be at least 2 characters long")
    private String lastName;

    @NotNull
    @Email
    private String email;
}
