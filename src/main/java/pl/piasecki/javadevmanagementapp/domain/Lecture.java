package pl.piasecki.javadevmanagementapp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    private String topic;
    private String localization;
    private Date date;

    @OneToMany(mappedBy = "lecture")
    Set<LectureStudent> lectureStudents = new HashSet<>();

    public void addStudent(Student student){
        LectureStudent lectureStudent = new LectureStudent();
        lectureStudent.setStudent(student);
        lectureStudent.setLecture(this);
        lectureStudents.add(lectureStudent);
    }
}
