package pl.piasecki.javadevmanagementapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/lecture/{lectureId}/student/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public LectureStudentDTO getLectureStudent(@PathVariable Long lectureId, @PathVariable Long studentId){
        return lectureStudentService.getLectureStudent(lectureId, studentId);
    }

    @PutMapping("/lecture/{lectureId}/student/{studentId}/set/{grade}/")
    @ResponseStatus(HttpStatus.OK)
    public LectureStudentDTO insetGrade(@PathVariable Long lectureId,
                                        @PathVariable Long studentId,
                                        @PathVariable Double grade){
        return lectureStudentService.insetGrade(lectureId, studentId, grade);
    }

    @PutMapping("/lecture/{lectureId}/student/{studentId}/delete")
    @ResponseStatus(HttpStatus.OK)
    public LectureStudentDTO deleteGrade(@PathVariable Long lectureId, @PathVariable Long studentId){
        return lectureStudentService.deleteGrade(lectureId, studentId);
    }
}
