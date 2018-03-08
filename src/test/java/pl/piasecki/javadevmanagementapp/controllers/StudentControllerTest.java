package pl.piasecki.javadevmanagementapp.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.piasecki.javadevmanagementapp.api.model.*;
import pl.piasecki.javadevmanagementapp.domain.Lecture;
import pl.piasecki.javadevmanagementapp.services.StudentService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.piasecki.javadevmanagementapp.controllers.AbstractRestControllerTest.asJsonString;
import static pl.piasecki.javadevmanagementapp.controllers.StudentController.BASE_URL;

public class StudentControllerTest {

    public static final String FIRST_NAME = "Adam";
    public static final String LAST_NAME = "Malysz";
    public static final String EMAIL = "adam.malysz@gmail.com";
    public static final String TOPIC = "Spring Framework";

    @Mock
    StudentService studentService;

    @InjectMocks
    StudentController studentController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @Test
    public void getAllStudents() throws Exception {
        List<StudentDTO> studentDTOS = Arrays.asList(new StudentDTO(), new StudentDTO());
        when(studentService.getAllStudents()).thenReturn(studentDTOS);

        mockMvc.perform(get(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void getStudentById() throws Exception {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setFirstName(FIRST_NAME);
        studentDTO.setEmail(EMAIL);

        when(studentService.getStudentById(anyLong())).thenReturn(studentDTO);

        mockMvc.perform(get(BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)));
    }

    @Test
    public void createNewStudent() throws Exception {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setEmail(EMAIL);

        StudentDTO savedDTO = new StudentDTO();
        savedDTO.setEmail(studentDTO.getEmail());

        when(studentService.createNewStudent(any(StudentDTO.class))).thenReturn(savedDTO);

        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(studentDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email", equalTo(EMAIL)));
    }

    @Test
    public void updateStudent() throws Exception {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setEmail(EMAIL);

        StudentDTO savedDTO = new StudentDTO();
        savedDTO.setEmail(studentDTO.getEmail());

        when(studentService.updateStudent(anyLong(), any(StudentDTO.class))).thenReturn(savedDTO);

        mockMvc.perform(put(BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(studentDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", equalTo(EMAIL)));
    }

    @Test
    public void deleteStudent() throws Exception {
        mockMvc.perform(delete(BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(studentService).deleteStudent(1L);
    }

    @Test
    public void getStudentLectures() throws Exception {
        List<LSLectureDTO> lsLectureDTOS = Arrays.asList(new LSLectureDTO(), new LSLectureDTO());

        when(studentService.getStudentLectures(anyLong())).thenReturn(lsLectureDTOS);

        mockMvc.perform(get(BASE_URL + "/1/lectures")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void getStudentsByFirstName() throws Exception {
        StudentDTO studentDTO1 = new StudentDTO();
        studentDTO1.setFirstName(FIRST_NAME);

        StudentDTO studentDTO2 = new StudentDTO();
        studentDTO2.setFirstName(FIRST_NAME);

        List<StudentDTO> studentDTOS = Arrays.asList(studentDTO1, studentDTO2);

        when(studentService.getStudentsByFirstName(anyString())).thenReturn(studentDTOS);

        mockMvc.perform(get(BASE_URL + "/search/by-first-name/Adam/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$[1].firstName", equalTo(FIRST_NAME)));
    }

    @Test
    public void getStudentsByLastName() throws Exception {
        StudentDTO studentDTO1 = new StudentDTO();
        studentDTO1.setLastName(LAST_NAME);

        StudentDTO studentDTO2 = new StudentDTO();
        studentDTO2.setLastName(LAST_NAME);

        List<StudentDTO> studentDTOS = Arrays.asList(studentDTO1, studentDTO2);

        when(studentService.getStudentsByLastName(anyString())).thenReturn(studentDTOS);

        mockMvc.perform(get(BASE_URL + "/search/by-last-name/Malysz/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].lastName", equalTo(LAST_NAME)))
                .andExpect(jsonPath("$[1].lastName", equalTo(LAST_NAME)));
    }

    @Test
    public void getStudentsByFirstNameAndLastName() throws Exception {
        StudentDTO studentDTO1 = new StudentDTO();
        studentDTO1.setFirstName(FIRST_NAME);
        studentDTO1.setLastName(LAST_NAME);

        StudentDTO studentDTO2 = new StudentDTO();
        studentDTO2.setFirstName(FIRST_NAME);
        studentDTO2.setLastName(LAST_NAME);

        List<StudentDTO> studentDTOS = Arrays.asList(studentDTO1, studentDTO2);

        when(studentService.getStudentsByFirstNameAndLastName(anyString(), anyString())).thenReturn(studentDTOS);

        mockMvc.perform(get(BASE_URL + "/search/by-first-name/Adam/and-last-name/Malysz/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$[1].firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$[0].lastName", equalTo(LAST_NAME)))
                .andExpect(jsonPath("$[1].lastName", equalTo(LAST_NAME)));
    }

    @Test
    public void getStudentByEmail() throws Exception {

        LectureDTO lectureDTO = new LectureDTO();
        lectureDTO.setTopic(TOPIC);

        Double grade = 3.5;

        LSLectureAndGradeDTO lsLectureAndGradeDTO = new LSLectureAndGradeDTO();
        lsLectureAndGradeDTO.setGrade(grade);
        lsLectureAndGradeDTO.setLecture(lectureDTO);

        Set<LSLectureAndGradeDTO> lsLectureAndGradeDTOS = new HashSet<>();
        lsLectureAndGradeDTOS.add(lsLectureAndGradeDTO);

        StudentWithLecturesAndGradesDTO studentWithLecturesAndGradesDTO = new StudentWithLecturesAndGradesDTO();
        studentWithLecturesAndGradesDTO.setEmail(EMAIL);
        studentWithLecturesAndGradesDTO.setLecturesAndGrades(lsLectureAndGradeDTOS);

        when(studentService.getStudentByEmail(anyString())).thenReturn(studentWithLecturesAndGradesDTO);

        mockMvc.perform(get(BASE_URL + "/search/by-email/ddd@gmial.com/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", equalTo(EMAIL)))
                .andExpect(jsonPath("$.lecturesAndGrades", hasSize(1)))
                .andExpect(jsonPath("$.lecturesAndGrades[0].grade", equalTo(grade)));
    }
}