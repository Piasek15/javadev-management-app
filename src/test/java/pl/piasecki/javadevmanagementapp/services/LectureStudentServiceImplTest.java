package pl.piasecki.javadevmanagementapp.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.piasecki.javadevmanagementapp.api.mapper.LectureStudentMapper;
import pl.piasecki.javadevmanagementapp.api.model.LectureStudentDTO;
import pl.piasecki.javadevmanagementapp.domain.LectureStudent;
import pl.piasecki.javadevmanagementapp.repositories.LectureStudentRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class LectureStudentServiceImplTest {

    public static final String TOPIC = "Spring Framework";
    public static final String FIRST_NAME = "Adam";
    public static final long ID = 1L;
    public static final double GRADE = 3.5;

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

}