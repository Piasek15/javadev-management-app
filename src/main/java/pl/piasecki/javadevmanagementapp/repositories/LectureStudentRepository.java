package pl.piasecki.javadevmanagementapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piasecki.javadevmanagementapp.domain.Lecture;
import pl.piasecki.javadevmanagementapp.domain.LectureStudent;
import pl.piasecki.javadevmanagementapp.domain.Student;

public interface LectureStudentRepository extends JpaRepository<LectureStudent, Long>{
    void deleteByLectureAndStudent(Lecture lecture, Student student);
    LectureStudent findLectureStudentByLectureAndStudent(Lecture lecture, Student student);
}
