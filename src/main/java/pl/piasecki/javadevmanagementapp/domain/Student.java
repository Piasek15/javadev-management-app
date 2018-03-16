package pl.piasecki.javadevmanagementapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "STUDENTS")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STUDENT_ID")
    private Long id;

    @NotNull
    @Size(min = 2, message = "First name should be at least 2 characters long")
    private String firstName;

    @NotNull
    @Size(min = 2, message = "Last name should be at least 2 characters long")
    private String lastName;

    @NotNull
    @Email
    private String email;

    @OneToMany(mappedBy = "student")
    private Set<LectureStudent> lectureStudents = new HashSet<>();
}
