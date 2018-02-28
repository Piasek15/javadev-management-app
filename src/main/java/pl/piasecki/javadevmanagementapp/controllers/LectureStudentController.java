package pl.piasecki.javadevmanagementapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.piasecki.javadevmanagementapp.api.model.LectureStudentDTO;
import pl.piasecki.javadevmanagementapp.services.LectureStudentService;

import java.util.List;

import static pl.piasecki.javadevmanagementapp.controllers.LectureStudentController.BASE_URL;

@RestController
@RequestMapping(BASE_URL)
public class LectureStudentController {
    public static final String BASE_URL = "/grades";

    private final LectureStudentService lectureStudentService;

    public LectureStudentController(LectureStudentService lectureStudentService) {
        this.lectureStudentService = lectureStudentService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LectureStudentDTO> getAllLectureStudents(){
        return lectureStudentService.getAllLectureStudents();
    }
}
