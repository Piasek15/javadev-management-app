package pl.piasecki.javadevmanagementapp.services;

import pl.piasecki.javadevmanagementapp.api.model.LectureDTO;
import pl.piasecki.javadevmanagementapp.api.model.StudentDTO;

import java.util.List;

public interface StudentService {

    List<StudentDTO> getAllStudents();
    StudentDTO getStudentById(Long id);
    StudentDTO createNewStudent(StudentDTO studentDTO);
    StudentDTO updateStudent(Long id, StudentDTO studentDTO);
    void deleteStudent(Long id);
    List<LectureDTO> getStudentLectures(Long studentId);
}
