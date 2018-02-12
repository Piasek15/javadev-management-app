package pl.piasecki.javadevmanagementapp.api.mapper;

import org.junit.Test;
import pl.piasecki.javadevmanagementapp.api.model.LectureDTO;
import pl.piasecki.javadevmanagementapp.domain.Lecture;

import java.util.Date;

import static org.junit.Assert.*;

public class LectureMapperTest {

    public static final String TOPIC = "Spring Framework";
    public static final String LOC = "Rzeszow WSIZ";
    public Date date = new Date(2018, 1, 15);

    LectureMapper lectureMapper = LectureMapper.INSTANCE;


    @Test
    public void lectureToLectureDTO() throws Exception {
        Lecture lecture = new Lecture();
        lecture.setTopic(TOPIC);
        lecture.setLocalization(LOC);
        lecture.setDate(date);

        LectureDTO lectureDTO = lectureMapper.lectureToLectureDTO(lecture);

        assertEquals(TOPIC, lectureDTO.getTopic());
        assertEquals(LOC, lectureDTO.getLocalization());
        assertEquals(date, lectureDTO.getDate());
    }

    @Test
    public void lectureDTOToLecture() throws Exception {
        LectureDTO lectureDTO = new LectureDTO();
        lectureDTO.setTopic(TOPIC);
        lectureDTO.setLocalization(LOC);
        lectureDTO.setDate(date);

        Lecture lecture = lectureMapper.lectureDTOToLecture(lectureDTO);

        assertEquals(TOPIC, lecture.getTopic());
        assertEquals(LOC, lecture.getLocalization());
        assertEquals(date, lecture.getDate());
    }

}