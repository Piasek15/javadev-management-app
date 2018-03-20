package pl.piasecki.javadevmanagementapp.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.piasecki.javadevmanagementapp.api.model.LectureStudentDTO;
import pl.piasecki.javadevmanagementapp.services.LectureStudentService;

import java.util.List;

import static pl.piasecki.javadevmanagementapp.controllers.LectureStudentController.BASE_URL;

@Api(description = "Grade Controller")
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

    @ApiOperation(value = "Get grade by lecture ID and student ID.")
    @GetMapping("/lecture/{lectureId}/student/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public LectureStudentDTO getLectureStudent(@PathVariable Long lectureId, @PathVariable Long studentId){
        return lectureStudentService.getLectureStudent(lectureId, studentId);
    }

    @ApiOperation(value = "Set grade.")
    @PutMapping("/lecture/{lectureId}/student/{studentId}/set/{grade}/")
    @ResponseStatus(HttpStatus.OK)
    public LectureStudentDTO insetGrade(@PathVariable Long lectureId,
                                        @PathVariable Long studentId,
                                        @PathVariable Double grade){
        return lectureStudentService.insetGrade(lectureId, studentId, grade);
    }

    @ApiOperation(value = "Delete grade.")
    @PutMapping("/lecture/{lectureId}/student/{studentId}/delete")
    @ResponseStatus(HttpStatus.OK)
    public LectureStudentDTO deleteGrade(@PathVariable Long lectureId, @PathVariable Long studentId){
        return lectureStudentService.deleteGrade(lectureId, studentId);
    }

    @ApiOperation(value = "Get list of students by grade")
    @GetMapping("/grade/{grade}/")
    @ResponseStatus(HttpStatus.OK)
    public List<LectureStudentDTO> getLectureStudentsByGrade(@PathVariable Double grade){
        return lectureStudentService.getLectureStudentsByGrade(grade);
    }
}
