package pl.piasecki.javadevmanagementapp.services;

import org.springframework.stereotype.Service;
import pl.piasecki.javadevmanagementapp.api.mapper.LectureStudentMapper;
import pl.piasecki.javadevmanagementapp.api.mapper.StudentMapper;
import pl.piasecki.javadevmanagementapp.api.model.LSLectureDTO;
import pl.piasecki.javadevmanagementapp.api.model.StudentDTO;
import pl.piasecki.javadevmanagementapp.domain.Student;
import pl.piasecki.javadevmanagementapp.repositories.LectureStudentRepository;
import pl.piasecki.javadevmanagementapp.repositories.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentMapper studentMapper;
    private final LectureStudentMapper lectureStudentMapper;
    private final StudentRepository studentRepository;
    private final LectureStudentRepository lectureStudentRepository;

    public StudentServiceImpl(StudentMapper studentMapper, LectureStudentMapper lectureStudentMapper, StudentRepository studentRepository, LectureStudentRepository lectureStudentRepository) {
        this.studentMapper = studentMapper;
        this.lectureStudentMapper = lectureStudentMapper;
        this.studentRepository = studentRepository;
        this.lectureStudentRepository = lectureStudentRepository;
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

    @Override
    public List<LSLectureDTO> getStudentLectures(Long studentId) {
        return lectureStudentRepository.findAllByStudent_Id(studentId)
                .stream()
                .map(lectureStudentMapper::lectureStudentToLSLectureDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> getStudentsByFirstName(String firstName) {
        return studentRepository.findAllByFirstName(firstName)
                .stream()
                .map(studentMapper::studentToStudentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> getStudentsByLastName(String lastName) {
        return studentRepository.findAllByLastName(lastName)
                .stream()
                .map(studentMapper::studentToStudentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> getStudentsByFirstNameAndLastName(String firstName, String lastName) {
        return studentRepository.findAllByFirstNameAndLastName(firstName, lastName)
                .stream()
                .map(studentMapper::studentToStudentDTO)
                .collect(Collectors.toList());
    }


}
