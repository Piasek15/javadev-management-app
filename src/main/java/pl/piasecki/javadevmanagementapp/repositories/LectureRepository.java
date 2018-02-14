package pl.piasecki.javadevmanagementapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pl.piasecki.javadevmanagementapp.domain.Lecture;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
