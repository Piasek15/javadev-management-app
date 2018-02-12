package pl.piasecki.javadevmanagementapp.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.piasecki.javadevmanagementapp.domain.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
