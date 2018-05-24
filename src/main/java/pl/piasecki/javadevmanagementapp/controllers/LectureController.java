package pl.piasecki.javadevmanagementapp.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.piasecki.javadevmanagementapp.api.model.LSStudentDTO;
import pl.piasecki.javadevmanagementapp.api.model.LectureDTO;
import pl.piasecki.javadevmanagementapp.api.model.LectureWStudentListDTO;
import pl.piasecki.javadevmanagementapp.api.model.StudentDTO;
import pl.piasecki.javadevmanagementapp.services.LectureService;

import javax.validation.Valid;
import java.util.List;

import static pl.piasecki.javadevmanagementapp.controllers.LectureController.BASE_URL;

@RestController
@RequestMapping(BASE_URL)
public class LectureController {
    public static final String BASE_URL = "/lectures";

    private final LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @ApiOperation(value = "Get a list of lectures.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LectureDTO> getAllLectures(){
        return lectureService.getAllLectures();
    }

    @ApiOperation(value = "Get lecture.", notes = "Get all information about one lecture, by ID.")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LectureWStudentListDTO getLectureById(@PathVariable Long id){
        return lectureService.getLectureById(id);
    }

    @ApiOperation(value = "Create new lecture.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LectureDTO createNewLecture(@RequestBody LectureDTO lectureDTO){
        return lectureService.createNewLecture(lectureDTO);
    }

    @ApiOperation(value = "Update existing lecture.")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LectureDTO updateLecture(@PathVariable Long id, @RequestBody LectureDTO lectureDTO){
        return lectureService.updateLecture(id, lectureDTO);
    }

    @ApiOperation(value = "Delete lecture.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLecture(@PathVariable Long id){
        lectureService.deleteLecture(id);
    }

    @ApiOperation(value = "Add student to lecture.", notes = "Adding student makes him present on lecture and give opportunity to set a grade.")
    @PostMapping("/{lectureId}/students/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public LectureWStudentListDTO addStudentToLecture(@PathVariable Long lectureId, @PathVariable Long studentId){
        return lectureService.addStudentToLecture(lectureId, studentId);
    }

    @ApiOperation(value = "Delete student from lecture.")
    @DeleteMapping("/{lectureId}/students/{studentId}")
    @ResponseStatus(HttpStatus.OK)
    public LectureWStudentListDTO deleteStudentFromLecture(@PathVariable Long lectureId, @PathVariable Long studentId){
        return lectureService.deleteStudentFromLecture(lectureId, studentId);
    }

    @ApiOperation(value = "Get list of attendant students.")
    @GetMapping("/{lectureId}/students")
    @ResponseStatus(HttpStatus.OK)
    public List<LSStudentDTO> getLectureStudents(@PathVariable Long lectureId){
        return lectureService.getLectureStudents(lectureId);
    }

}
