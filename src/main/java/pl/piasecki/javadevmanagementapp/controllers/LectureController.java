package pl.piasecki.javadevmanagementapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.piasecki.javadevmanagementapp.api.model.LectureDTO;
import pl.piasecki.javadevmanagementapp.services.LectureService;

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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LectureDTO> getAllLectures(){
        return lectureService.getAllLectures();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LectureDTO getLectureById(@PathVariable Long id){
        return lectureService.getLectureById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LectureDTO createNewLecture(@RequestBody LectureDTO lectureDTO){
        return lectureService.createNewLecture(lectureDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LectureDTO updateLecture(@PathVariable Long id, @RequestBody LectureDTO lectureDTO){
        return lectureService.updateLecture(id, lectureDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLecture(@PathVariable Long id){
        lectureService.deleteLecture(id);
    }

    @PutMapping("/{lectureId}/students/{studentId}/add")
    @ResponseStatus(HttpStatus.OK)
    public LectureDTO addStudentToLecture(@PathVariable Long lectureId, @PathVariable Long studentId){
        return lectureService.addStudentToLecture(lectureId, studentId);
    }

    @PutMapping("/{lectureId}/students/{studentId}/delete")
    @ResponseStatus(HttpStatus.OK)
    public LectureDTO deleteStudentFromLecture(@PathVariable Long lectureId, @PathVariable Long studentId){
        return lectureService.deleteStudentFromLecture(lectureId, studentId);
    }

}
