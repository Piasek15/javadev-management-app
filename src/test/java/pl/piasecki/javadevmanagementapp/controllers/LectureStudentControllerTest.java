package pl.piasecki.javadevmanagementapp.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.piasecki.javadevmanagementapp.api.model.LectureDTO;
import pl.piasecki.javadevmanagementapp.api.model.LectureStudentDTO;
import pl.piasecki.javadevmanagementapp.api.model.StudentDTO;
import pl.piasecki.javadevmanagementapp.services.LectureStudentService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.piasecki.javadevmanagementapp.controllers.LectureStudentController.BASE_URL;

public class LectureStudentControllerTest {

    public static final String TOPIC = "Spring Framework";
    public static final String FIRST_NAME = "Adam";
    public static final long ID = 1L;
    public static final double GRADE = 3.5;

    @Mock
    LectureStudentService lectureStudentService;

    @InjectMocks
    LectureStudentController lectureStudentController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(lectureStudentController).build();
    }

    @Test
    public void getAllLectureStudents() throws Exception {
        List<LectureStudentDTO> lectureStudentDTOS = Arrays
                .asList(new LectureStudentDTO(), new LectureStudentDTO());

        when(lectureStudentService.getAllLectureStudents()).thenReturn(lectureStudentDTOS);

        mockMvc.perform(get(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void getLectureStudent() throws Exception {
        LectureDTO lectureDTO = new LectureDTO();
        lectureDTO.setTopic(TOPIC);

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setFirstName(FIRST_NAME);

        LectureStudentDTO lectureStudentDTO = new LectureStudentDTO();
        lectureStudentDTO.setGrade(GRADE);
        lectureStudentDTO.setStudent(studentDTO);
        lectureStudentDTO.setLecture(lectureDTO);

        when(lectureStudentService.getLectureStudent(anyLong(), anyLong())).thenReturn(lectureStudentDTO);

        mockMvc.perform(get(BASE_URL + "/lecture/1/student/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.grade", equalTo(3.5)))
                .andExpect(jsonPath("$.student.firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.lecture.topic", equalTo(TOPIC)));
    }

    @Test
    public void insetGrade() throws Exception {
        LectureDTO lectureDTO = new LectureDTO();
        lectureDTO.setTopic(TOPIC);

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setFirstName(FIRST_NAME);

        LectureStudentDTO lectureStudentDTO = new LectureStudentDTO();
        lectureStudentDTO.setGrade(GRADE);
        lectureStudentDTO.setStudent(studentDTO);
        lectureStudentDTO.setLecture(lectureDTO);

        when(lectureStudentService.insetGrade(anyLong(), anyLong(), anyDouble())).thenReturn(lectureStudentDTO);

        mockMvc.perform(put(BASE_URL + "/lecture/1/student/1/set/3.5/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.grade", equalTo(3.5)))
                .andExpect(jsonPath("$.student.firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.lecture.topic", equalTo(TOPIC)));
    }

    @Test
    public void deleteGrade() throws Exception {
        LectureDTO lectureDTO = new LectureDTO();
        lectureDTO.setTopic(TOPIC);

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setFirstName(FIRST_NAME);

        LectureStudentDTO lectureStudentDTO = new LectureStudentDTO();
        lectureStudentDTO.setGrade(GRADE);
        lectureStudentDTO.setStudent(studentDTO);
        lectureStudentDTO.setLecture(lectureDTO);
        lectureStudentDTO.setGrade(null);

        when(lectureStudentService.deleteGrade(anyLong(), anyLong())).thenReturn(lectureStudentDTO);

        mockMvc.perform(put(BASE_URL + "/lecture/1/student/1/delete")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.grade", equalTo(null)))
                .andExpect(jsonPath("$.student.firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.lecture.topic", equalTo(TOPIC)));
    }

    @Test
    public void getLectureStudentsByGrade() throws Exception {
        LectureStudentDTO lectureStudentDTO1 = new LectureStudentDTO();
        lectureStudentDTO1.setGrade(GRADE);

        LectureStudentDTO lectureStudentDTO2 = new LectureStudentDTO();
        lectureStudentDTO2.setGrade(GRADE);

        List<LectureStudentDTO> lectureStudentDTOS = Arrays.asList(lectureStudentDTO1, lectureStudentDTO2);

        when(lectureStudentService.getLectureStudentsByGrade(anyDouble())).thenReturn(lectureStudentDTOS);

        mockMvc.perform(get(BASE_URL + "/grade/1/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].grade", equalTo(GRADE)))
                .andExpect(jsonPath("$[1].grade", equalTo(GRADE)));
    }
}