package pl.piasecki.javadevmanagementapp.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import pl.piasecki.javadevmanagementapp.api.model.StudentDTO;
import pl.piasecki.javadevmanagementapp.api.model.StudentWithLecturesAndGradesDTO;
import pl.piasecki.javadevmanagementapp.domain.Student;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);
    StudentDTO studentToStudentDTO(Student student);
    Student studentDTOToStudent(StudentDTO studentDTO);

    @Mapping(target = "lecturesAndGrades", source = "lectureStudents")
    StudentWithLecturesAndGradesDTO studentToStudentWithLecturesAndGradesDTO(Student student);
}
