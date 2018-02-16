package pl.piasecki.javadevmanagementapp.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.piasecki.javadevmanagementapp.api.mapper.StudentMapper;
import pl.piasecki.javadevmanagementapp.api.model.StudentDTO;
import pl.piasecki.javadevmanagementapp.domain.Lecture;
import pl.piasecki.javadevmanagementapp.domain.Student;
import pl.piasecki.javadevmanagementapp.repositories.LectureRepository;
import pl.piasecki.javadevmanagementapp.repositories.StudentRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

public class StudentServiceImplTest {


    public static final String FIRST_NAME = "Adam";
    public static final String LAST_NAME = "Malysz";
    public static final String EMAIL = "adam.malysz@gmail.com";
    public static final long ID = 1L;
    public static final String TOPIC = "Spring Framework";

    StudentService studentService;

    @Mock
    StudentRepository studentRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        studentService = new StudentServiceImpl(StudentMapper.INSTANCE, studentRepository);
    }

    @Test
    public void getAllStudents() throws Exception {
        List<Student> students = Arrays.asList(new Student(), new Student());
        when(studentRepository.findAll()).thenReturn(students);

        List<StudentDTO> studentDTOS = studentService.getAllStudents();

        assertEquals(2, studentDTOS.size());
    }

    @Test
    public void getStudentById() throws Exception {
        Student student = new Student();
        student.setId(ID);
        student.setFirstName(FIRST_NAME);
        student.setEmail(EMAIL);

        when(studentRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(student));

        StudentDTO studentDTO = studentService.getStudentById(ID);

        assertEquals(FIRST_NAME, studentDTO.getFirstName());
        assertEquals(EMAIL, studentDTO.getEmail());
    }

    @Test
    public void createNewStudent() throws Exception {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setEmail(EMAIL);

        Student student = new Student();
        student.setEmail(studentDTO.getEmail());

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        StudentDTO savedDTO = studentService.createNewStudent(studentDTO);

        assertEquals(studentDTO.getEmail(), savedDTO.getEmail());
    }

    @Test
    public void updateStudent() throws Exception {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setEmail(EMAIL);

        Student student = new Student();
        student.setEmail(studentDTO.getEmail());

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        StudentDTO savedDTO = studentService.updateStudent(anyLong(), studentDTO);

        assertEquals(EMAIL, savedDTO.getEmail());
    }

    @Test
    public void deleteStudent() throws Exception {
        studentRepository.deleteById(1L);

        verify(studentRepository, times(1)).deleteById(anyLong());
    }

//    @Test
//    public void addLectureToStudent() throws Exception {
//        Lecture lecture = new Lecture();
//        lecture.setTopic(TOPIC);
//        lecture.setId(ID);
//
//        Set<Lecture> lectures = new HashSet<>();
//        lectures.add(lecture);
//
//        Student student = new Student();
//        student.setFirstName(FIRST_NAME);
//        student.setLectures(lectures);
//
//        when(lectureRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(lecture));
//        when(studentRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(student));
//
//
//        StudentDTO studentDTO = studentService.addLectureToStudent(ID, anyLong());
//
//        assertEquals(FIRST_NAME, studentDTO.getFirstName());
//        assertEquals(1, studentDTO.getLectures().size());
//        assertEquals(TOPIC, studentDTO.getLectures().iterator().next().getTopic());
//    }
}