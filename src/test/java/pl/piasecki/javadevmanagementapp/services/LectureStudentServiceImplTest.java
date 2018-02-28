package pl.piasecki.javadevmanagementapp.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.piasecki.javadevmanagementapp.api.mapper.LectureStudentMapper;
import pl.piasecki.javadevmanagementapp.api.model.LectureStudentDTO;
import pl.piasecki.javadevmanagementapp.domain.Lecture;
import pl.piasecki.javadevmanagementapp.domain.LectureStudent;
import pl.piasecki.javadevmanagementapp.domain.Student;
import pl.piasecki.javadevmanagementapp.repositories.LectureStudentRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class LectureStudentServiceImplTest {

    public static final String TOPIC = "Spring Framework";
    public static final String FIRST_NAME = "Adam";
    public static final long ID = 1L;
    public static final Double GRADE = 3.5;

    LectureStudentService lectureStudentService;

    @Mock
    LectureStudentRepository lectureStudentRepository;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        lectureStudentService = new LectureStudentServiceImpl(LectureStudentMapper.INSTANCE, lectureStudentRepository);
    }

    @Test
    public void getAllLectureStudents() throws Exception {
        List<LectureStudent> lectureStudents = Arrays.asList(new LectureStudent(), new LectureStudent());

        when(lectureStudentRepository.findAll()).thenReturn(lectureStudents);

        List<LectureStudentDTO> lectureStudentDTOS = lectureStudentService.getAllLectureStudents();

        assertEquals(2, lectureStudentDTOS.size());
    }

    @Test
    public void getLectureStudent() throws Exception {
        Lecture lecture = new Lecture();
        lecture.setTopic(TOPIC);

        Student student = new Student();
        student.setFirstName(FIRST_NAME);

        LectureStudent lectureStudent = new LectureStudent();
        lectureStudent.setGrade(GRADE);
        lectureStudent.setLecture(lecture);
        lectureStudent.setStudent(student);

        when(lectureStudentRepository.findByLecture_IdAndStudent_Id(anyLong(), anyLong()))
                .thenReturn(lectureStudent);

        LectureStudentDTO lectureStudentDTO = lectureStudentService.getLectureStudent(anyLong(), anyLong());

        assertEquals(GRADE, lectureStudentDTO.getGrade());
        assertEquals(TOPIC, lectureStudentDTO.getLecture().getTopic());
        assertEquals(FIRST_NAME, lectureStudentDTO.getStudent().getFirstName());
    }

    @Test
    public void insetGrade() throws Exception {
        Lecture lecture = new Lecture();
        lecture.setTopic(TOPIC);

        Student student = new Student();
        student.setFirstName(FIRST_NAME);

        LectureStudent lectureStudent = new LectureStudent();
        lectureStudent.setGrade(GRADE);
        lectureStudent.setLecture(lecture);
        lectureStudent.setStudent(student);

        when(lectureStudentRepository.findByLecture_IdAndStudent_Id(anyLong(), anyLong()))
                .thenReturn(lectureStudent);

        LectureStudentDTO lectureStudentDTO = lectureStudentService.insetGrade(anyLong(), anyLong(), GRADE);

        assertEquals(GRADE, lectureStudentDTO.getGrade());
        assertEquals(TOPIC, lectureStudentDTO.getLecture().getTopic());
        assertEquals(FIRST_NAME, lectureStudentDTO.getStudent().getFirstName());
    }

    @Test
    public void deleteGrade() throws Exception {
        Lecture lecture = new Lecture();
        lecture.setTopic(TOPIC);

        Student student = new Student();
        student.setFirstName(FIRST_NAME);

        LectureStudent lectureStudent = new LectureStudent();
        lectureStudent.setGrade(GRADE);
        lectureStudent.setLecture(lecture);
        lectureStudent.setStudent(student);
        lectureStudent.setGrade(null);

        when(lectureStudentRepository.findByLecture_IdAndStudent_Id(anyLong(), anyLong()))
                .thenReturn(lectureStudent);

        LectureStudentDTO lectureStudentDTO = lectureStudentService.deleteGrade(anyLong(), anyLong());

        assertEquals(null, lectureStudentDTO.getGrade());
        assertEquals(TOPIC, lectureStudentDTO.getLecture().getTopic());
        assertEquals(FIRST_NAME, lectureStudentDTO.getStudent().getFirstName());
    }
}