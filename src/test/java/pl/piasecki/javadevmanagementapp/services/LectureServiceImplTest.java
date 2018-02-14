package pl.piasecki.javadevmanagementapp.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.piasecki.javadevmanagementapp.api.mapper.LectureMapper;
import pl.piasecki.javadevmanagementapp.api.model.LectureDTO;
import pl.piasecki.javadevmanagementapp.domain.Lecture;
import pl.piasecki.javadevmanagementapp.repositories.LectureRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LectureServiceImplTest {

    public static final String TOPIC = "Spring Framework";
    public static final String LOC = "Rzeszow WSIZ";
    public Date date = new Date(2018, 1, 15);

    LectureService lectureService;

    @Mock
    LectureRepository lectureRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        lectureService = new LectureServiceImpl(LectureMapper.INSTANCE, lectureRepository);
    }

    @Test
    public void getAllLectures() throws Exception {
        List<Lecture> lectures = Arrays.asList(new Lecture(), new Lecture());

        when(lectureRepository.findAll()).thenReturn(lectures);

        List<LectureDTO> lectureDTOS = lectureService.getAllLectures();

        assertEquals(2, lectureDTOS.size());
    }

    @Test
    public void getLectureById() throws Exception {
        Lecture lecture = new Lecture();
        lecture.setTopic(TOPIC);

        when(lectureRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(lecture));

        LectureDTO lectureDTO = lectureService.getLectureById(anyLong());

        assertEquals(TOPIC, lectureDTO.getTopic());
    }

    @Test
    public void createNewLecture() throws Exception {
        LectureDTO lectureDTO = new LectureDTO();
        lectureDTO.setTopic(TOPIC);

        Lecture lecture = new Lecture();
        lecture.setTopic(lectureDTO.getTopic());

        when(lectureRepository.save(any(Lecture.class))).thenReturn(lecture);

        LectureDTO savedDTO = lectureService.createNewLecture(lectureDTO);

        assertEquals(TOPIC, savedDTO.getTopic());
    }

    @Test
    public void updateLecture() throws Exception {
        LectureDTO lectureDTO = new LectureDTO();
        lectureDTO.setTopic(TOPIC);

        Lecture lecture = new Lecture();
        lecture.setTopic(lectureDTO.getTopic());

        when(lectureRepository.save(any(Lecture.class))).thenReturn(lecture);

        LectureDTO updatedDTO = lectureService.updateLecture(anyLong(), lectureDTO);

        assertEquals(TOPIC, updatedDTO.getTopic());
    }

    @Test
    public void deleteLecture() throws Exception {
        lectureRepository.deleteById(1L);
        verify(lectureRepository, times(1)).deleteById(anyLong());
    }
}