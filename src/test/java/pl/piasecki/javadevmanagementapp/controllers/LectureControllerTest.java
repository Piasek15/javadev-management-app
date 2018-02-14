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
import pl.piasecki.javadevmanagementapp.services.LectureService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.piasecki.javadevmanagementapp.controllers.AbstractRestControllerTest.asJsonString;
import static pl.piasecki.javadevmanagementapp.controllers.LectureController.BASE_URL;

public class LectureControllerTest {

    public static final String TOPIC = "Spring Framework";
    public static final String LOC = "Rzeszow WSIZ";
    public Date date = new Date(2018, 1, 15);

    @Mock
    LectureService lectureService;

    @InjectMocks
    LectureController lectureController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(lectureController).build();
    }

    @Test
    public void getAllLectures() throws Exception {
        List<LectureDTO> lectureDTOS = Arrays.asList(new LectureDTO(), new LectureDTO(), new LectureDTO());
        when(lectureService.getAllLectures()).thenReturn(lectureDTOS);

        mockMvc.perform(get(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void getLectureById() throws Exception {
        LectureDTO lectureDTO = new LectureDTO();
        lectureDTO.setTopic(TOPIC);

        when(lectureService.getLectureById(anyLong())).thenReturn(lectureDTO);

        mockMvc.perform(get(BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.topic", equalTo(TOPIC)));
    }

    @Test
    public void createNewLecture() throws Exception {
        LectureDTO lectureDTO = new LectureDTO();
        lectureDTO.setLocalization(LOC);

        LectureDTO savedDTO = new LectureDTO();
        savedDTO.setLocalization(lectureDTO.getLocalization());

        when(lectureService.createNewLecture(lectureDTO)).thenReturn(savedDTO);

        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(lectureDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.localization", equalTo(LOC)));
    }

    @Test
    public void updateLecture() throws Exception {
        LectureDTO lectureDTO = new LectureDTO();
        lectureDTO.setLocalization(LOC);

        LectureDTO savedDTO = new LectureDTO();
        savedDTO.setLocalization(lectureDTO.getLocalization());

        when(lectureService.updateLecture(anyLong(), any(LectureDTO.class))).thenReturn(savedDTO);

        mockMvc.perform(put(BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(lectureDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.localization", equalTo(LOC)));
    }

    @Test
    public void deleteLecture() throws Exception {
        mockMvc.perform(delete(BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(lectureService).deleteLecture(1L);
    }

}