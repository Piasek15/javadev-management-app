package pl.piasecki.javadevmanagementapp.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.piasecki.javadevmanagementapp.api.model.LSLectureDTO;
import pl.piasecki.javadevmanagementapp.api.model.LectureDTO;
import pl.piasecki.javadevmanagementapp.api.model.StudentDTO;
import pl.piasecki.javadevmanagementapp.api.model.StudentWithLecturesAndGradesDTO;
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

    @ApiOperation(value = "Get a list of students.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StudentDTO> getAllStudents(){
        return studentService.getAllStudents();
    }

    @ApiOperation(value = "Get student by ID.")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentDTO getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    @ApiOperation(value = "Create new student.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDTO createNewStudent(@RequestBody StudentDTO studentDTO){
        return studentService.createNewStudent(studentDTO);
    }

    @ApiOperation(value = "Update existing student.")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentDTO updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO){
        return studentService.updateStudent(id, studentDTO);
    }

    @ApiOperation(value = "Delete student.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
    }

    @ApiOperation(value = "Get list of lectures where student is present.")
    @GetMapping("/{studentId}/lectures")
    @ResponseStatus(HttpStatus.OK)
    public List<LSLectureDTO> getStudentLectures(@PathVariable Long studentId){
        return studentService.getStudentLectures(studentId);
    }

    @ApiOperation(value = "Find students by first name.")
    @GetMapping("/search/by-first-name/{firstName}/")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentDTO> getStudentsByFirstName(@PathVariable String firstName){
        return studentService.getStudentsByFirstName(firstName);
    }

    @ApiOperation(value = "Find students by last name.")
    @GetMapping("/search/by-last-name/{lastName}/")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentDTO> getStudentsByLastName(@PathVariable String lastName){
        return studentService.getStudentsByLastName(lastName);
    }

    @ApiOperation(value = "Find students by first name and last name.")
    @GetMapping("/search/by-first-name/{firstName}/and-last-name/{lastName}/")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentDTO> getStudentsByFirstNameAndLastName(@PathVariable String firstName,
                                                              @PathVariable String lastName){
        return studentService.getStudentsByFirstNameAndLastName(firstName, lastName);
    }

    @ApiOperation(value = "Get student by email.")
    @GetMapping("/search/by-email/{email}/")
    public StudentWithLecturesAndGradesDTO getStudentByEmail(@PathVariable String email){
        return studentService.getStudentByEmail(email);
    }

}
