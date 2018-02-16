package pl.piasecki.javadevmanagementapp.services;

import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import pl.piasecki.javadevmanagementapp.api.mapper.LectureMapper;
import pl.piasecki.javadevmanagementapp.api.mapper.StudentMapper;
import pl.piasecki.javadevmanagementapp.api.model.StudentDTO;
import pl.piasecki.javadevmanagementapp.domain.Lecture;
import pl.piasecki.javadevmanagementapp.domain.Student;
import pl.piasecki.javadevmanagementapp.repositories.LectureRepository;
import pl.piasecki.javadevmanagementapp.repositories.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentMapper studentMapper, StudentRepository studentRepository) {
        this.studentMapper = studentMapper;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::studentToStudentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        return studentRepository.findById(id)
                .map(studentMapper::studentToStudentDTO)
                .orElseThrow(RuntimeException::new); //TODO better exception
    }

    @Override
    public StudentDTO createNewStudent(StudentDTO studentDTO) {
        Student student = studentMapper.studentDTOToStudent(studentDTO);
        studentRepository.save(student);
        return studentMapper.studentToStudentDTO(student);
    }

    @Override
    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        Student student = studentMapper.studentDTOToStudent(studentDTO);
        student.setId(id);
        studentRepository.save(student);
        return studentMapper.studentToStudentDTO(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

//    @Override
//    public StudentDTO addLectureToStudent(Long studentId, Long lectureId) {
//        Student student = studentRepository.findById(studentId)
//                .orElseThrow(RuntimeException::new); //TODO better exception handler
//
//        Lecture lecture = lectureRepository.findById(lectureId)
//                .orElseThrow(RuntimeException::new); //TODO better exception handler
//
//        student.getLectures().add(lecture);
//        studentRepository.save(student);
//        return studentMapper.studentToStudentDTO(student);
//    }
}
