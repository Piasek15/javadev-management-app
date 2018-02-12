package pl.piasecki.javadevmanagementapp.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.piasecki.javadevmanagementapp.domain.Lecture;

public interface LectureRepository extends CrudRepository<Lecture, Long> {
}
