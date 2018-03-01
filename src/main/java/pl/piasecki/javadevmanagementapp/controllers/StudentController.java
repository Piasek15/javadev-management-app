package pl.piasecki.javadevmanagementapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.piasecki.javadevmanagementapp.api.model.LSLectureDTO;
import pl.piasecki.javadevmanagementapp.api.model.LectureDTO;
import pl.piasecki.javadevmanagementapp.api.model.StudentDTO;
import pl.piasecki.javadevmanagementapp.services.StudentService;

import java.util.Arrays;
import java.util.List;

import static pl.piasecki.javadevmanagementapp.controllers.StudentController.BASE_URL;


@RestController
@RequestMapping(BASE_URL)
public class StudentController {
    public static final String BASE_URL = "/students";

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StudentDTO> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentDTO getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDTO createNewStudent(@RequestBody StudentDTO studentDTO){
        return studentService.createNewStudent(studentDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentDTO updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO){
        return studentService.updateStudent(id, studentDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
    }

    @GetMapping("/{studentId}/lectures")
    @ResponseStatus(HttpStatus.OK)
    public List<LSLectureDTO> getStudentLectures(@PathVariable Long studentId){
        return studentService.getStudentLectures(studentId);
    }

    @GetMapping("/search/by-first-name/{firstName}/")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentDTO> getStudentsByFirstName(@PathVariable String firstName){
        return studentService.getStudentsByFirstName(firstName);
    }

    @GetMapping("/search/by-last-name/{lastName}/")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentDTO> getStudentsByLastName(@PathVariable String lastName){
        return studentService.getStudentsByLastName(lastName);
    }

    @GetMapping("/search/by-first-name/{firstName}/and-last-name/{lastName}/")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentDTO> getStudentsByFirstNameAndLastName(@PathVariable String firstName,
                                                              @PathVariable String lastName){
        return studentService.getStudentsByFirstNameAndLastName(firstName, lastName);
    }

}
