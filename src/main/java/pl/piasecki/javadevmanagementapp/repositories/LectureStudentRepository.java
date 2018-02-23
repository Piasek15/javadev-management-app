package pl.piasecki.javadevmanagementapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piasecki.javadevmanagementapp.domain.LectureStudent;

public interface LectureStudentRepository extends JpaRepository<LectureStudent, Long>{
}
