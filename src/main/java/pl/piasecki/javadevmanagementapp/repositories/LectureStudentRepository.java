package pl.piasecki.javadevmanagementapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piasecki.javadevmanagementapp.domain.Lecture;
import pl.piasecki.javadevmanagementapp.domain.LectureStudent;
import pl.piasecki.javadevmanagementapp.domain.Student;

import java.util.List;

public interface LectureStudentRepository extends JpaRepository<LectureStudent, Long>{
    void deleteByLectureAndStudent(Lecture lecture, Student student);
    LectureStudent findLectureStudentByLectureAndStudent(Lecture lecture, Student student);
    List<LectureStudent> findAllByLecture_Id(Long lectureId);
    List<LectureStudent> findAllByStudent_Id(Long studentId);
    LectureStudent findByLecture_IdAndStudent_Id(Long lectureId, Long studentId);
    List<LectureStudent> findAllByGrade(Double grade);
}
