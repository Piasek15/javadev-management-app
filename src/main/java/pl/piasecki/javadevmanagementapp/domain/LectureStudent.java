package pl.piasecki.javadevmanagementapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "LECTURES_STUDENTS")
public class LectureStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LECTURE_STUDENT_ID")
    private Long id;

    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "LECTURE_ID")
    private Lecture lecture;

    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "STUDENT_ID")
    private Student student;

    @Min(value = 1)
    @Max(value = 6)
    private Double grade;


}
