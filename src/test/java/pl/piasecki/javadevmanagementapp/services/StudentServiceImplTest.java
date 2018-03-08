package pl.piasecki.javadevmanagementapp.services;

import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.piasecki.javadevmanagementapp.api.mapper.LectureStudentMapper;
import pl.piasecki.javadevmanagementapp.api.mapper.StudentMapper;
import pl.piasecki.javadevmanagementapp.api.model.LSLectureDTO;
import pl.piasecki.javadevmanagementapp.api.model.StudentDTO;
import pl.piasecki.javadevmanagementapp.api.model.StudentWithLecturesAndGradesDTO;
import pl.piasecki.javadevmanagementapp.domain.Lecture;
import pl.piasecki.javadevmanagementapp.domain.LectureStudent;
import pl.piasecki.javadevmanagementapp.domain.Student;
import pl.piasecki.javadevmanagementapp.repositories.LectureStudentRepository;
import pl.piasecki.javadevmanagementapp.repositories.StudentRepository;

import java.util.*;

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

    @Mock
    LectureStudentRepository lectureStudentRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        studentService = new StudentServiceImpl(StudentMapper.INSTANCE, LectureStudentMapper.INSTANCE, studentRepository, lectureStudentRepository);
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

    @Test
    public void getStudentLectures() throws Exception {
        List<LectureStudent> lectureStudents = Arrays
                .asList(new LectureStudent(), new LectureStudent(), new LectureStudent());

        when(lectureStudentRepository.findAllByStudent_Id(anyLong())).thenReturn(lectureStudents);

        List<LSLectureDTO> lsLectureDTOS = studentService.getStudentLectures(anyLong());

        assertEquals(3, lsLectureDTOS.size());
    }

    @Test
    public void getStudentsByFirstName() throws Exception {
        Student student1 = new Student();
        student1.setFirstName(FIRST_NAME);

        Student student2 = new Student();
        student2.setFirstName(FIRST_NAME);

        List<Student> students = Arrays.asList(student1, student2);

        when(studentRepository.findAllByFirstName(anyString())).thenReturn(students);

        List<StudentDTO> studentDTOS = studentService.getStudentsByFirstName(anyString());

        assertEquals(2, studentDTOS.size());
        assertEquals(FIRST_NAME, studentDTOS.get(0).getFirstName());
        assertEquals(FIRST_NAME, studentDTOS.get(1).getFirstName());
    }

    @Test
    public void getStudentsByLastName() throws Exception {
        Student student1 = new Student();
        student1.setLastName(LAST_NAME);

        Student student2 = new Student();
        student2.setLastName(LAST_NAME);

        List<Student> students = Arrays.asList(student1, student2);

        when(studentRepository.findAllByLastName(anyString())).thenReturn(students);

        List<StudentDTO> studentDTOS = studentService.getStudentsByLastName(anyString());

        assertEquals(2, studentDTOS.size());
        assertEquals(LAST_NAME, studentDTOS.get(0).getLastName());
        assertEquals(LAST_NAME, studentDTOS.get(1).getLastName());
    }

    @Test
    public void getStudentsByFirstNameAndLastName() throws Exception {
        Student student1 = new Student();
        student1.setFirstName(FIRST_NAME);
        student1.setLastName(LAST_NAME);

        Student student2 = new Student();
        student2.setFirstName(FIRST_NAME);
        student2.setLastName(LAST_NAME);

        List<Student> students = Arrays.asList(student1, student2);

        when(studentRepository.findAllByFirstNameAndLastName(anyString(), anyString())).thenReturn(students);

        List<StudentDTO> studentDTOS = studentService.getStudentsByFirstNameAndLastName(anyString(), anyString());

        assertEquals(2, studentDTOS.size());
        assertEquals(FIRST_NAME, studentDTOS.get(0).getFirstName());
        assertEquals(FIRST_NAME, studentDTOS.get(1).getFirstName());
        assertEquals(LAST_NAME, studentDTOS.get(0).getLastName());
        assertEquals(LAST_NAME, studentDTOS.get(1).getLastName());
    }

    @Test
    public void getStudentByEmail() throws Exception {
        Student student = new Student();
        student.setFirstName(FIRST_NAME);
        student.setEmail(EMAIL);

        Lecture lecture = new Lecture();
        lecture.setTopic(TOPIC);

        Double grade = 3.5;

        LectureStudent lectureStudent = new LectureStudent();
        lectureStudent.setLecture(lecture);
        lectureStudent.setStudent(student);
        lectureStudent.setGrade(grade);

        Set<LectureStudent> lectureStudents = new HashSet<>();
        lectureStudents.add(lectureStudent);

        student.setLectureStudents(lectureStudents);

        when(studentRepository.findByEmail(anyString())).thenReturn(student);

        StudentWithLecturesAndGradesDTO studentWithLecturesAndGradesDTO = studentService.getStudentByEmail(anyString());

        assertEquals(1, studentWithLecturesAndGradesDTO.getLecturesAndGrades().size());
        assertEquals(grade, studentWithLecturesAndGradesDTO.getLecturesAndGrades().iterator().next().getGrade());
        assertEquals(EMAIL, studentWithLecturesAndGradesDTO.getEmail());
    }
}