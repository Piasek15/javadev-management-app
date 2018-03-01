package pl.piasecki.javadevmanagementapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pl.piasecki.javadevmanagementapp.domain.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByFirstName(String firstName);
    List<Student> findAllByLastName(String lastName);
}
