package pl.piasecki.javadevmanagementapp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "LECTURES")
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LECTURE_ID")
    private Long id;

    @NotNull
    @Size(min = 3, message = "Topic should be at least 3 characters long.")
    private String topic;

    @NotNull
    @Size(min = 3, message = "Localization should be at least 3 characters long.")
    private String localization;

    @Future
    private LocalDateTime date;

    @OneToMany(mappedBy = "lecture")
    Set<LectureStudent> lectureStudents = new HashSet<>();

    public LectureStudent addStudent(Student student){
        LectureStudent lectureStudent = new LectureStudent();
        lectureStudent.setStudent(student);
        lectureStudent.setLecture(this);
        return lectureStudent;
    }
}
