package pl.piasecki.javadevmanagementapp.api.mapper;

import org.junit.Test;
import pl.piasecki.javadevmanagementapp.api.model.StudentDTO;
import pl.piasecki.javadevmanagementapp.domain.Student;

import static org.junit.Assert.*;

public class StudentMapperTest {

    public static final String FIRST_NAME = "Adam";
    public static final String LAST_NAME = "Malysz";
    public static final String EMAIL = "adam.malysz@gmail.com";

    StudentMapper studentMapper = StudentMapper.INSTANCE;

    @Test
    public void studentToStudentDTO() throws Exception {
        Student student = new Student();
        student.setFirstName(FIRST_NAME);
        student.setLastName(LAST_NAME);
        student.setEmail(EMAIL);

        StudentDTO studentDTO = studentMapper.studentToStudentDTO(student);

        assertEquals(FIRST_NAME, studentDTO.getFirstName());
        assertEquals(LAST_NAME, studentDTO.getLastName());
        assertEquals(EMAIL, studentDTO.getEmail());
    }

    @Test
    public void studentDTOToStudent() throws Exception {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setFirstName(FIRST_NAME);
        studentDTO.setLastName(LAST_NAME);
        studentDTO.setEmail(EMAIL);

        Student student = studentMapper.studentDTOToStudent(studentDTO);

        assertEquals(FIRST_NAME, student.getFirstName());
        assertEquals(LAST_NAME, student.getLastName());
        assertEquals(EMAIL, student.getEmail());
    }

}