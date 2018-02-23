package pl.piasecki.javadevmanagementapp.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import pl.piasecki.javadevmanagementapp.api.model.LectureDTO;
import pl.piasecki.javadevmanagementapp.api.model.LectureWStudentListDTO;
import pl.piasecki.javadevmanagementapp.domain.Lecture;

@Mapper
public interface LectureMapper {
    LectureMapper INSTANCE = Mappers.getMapper(LectureMapper.class);
    LectureDTO lectureToLectureDTO(Lecture lecture);
    Lecture lectureDTOToLecture(LectureDTO lectureDTO);

    @Mapping(target = "students", source = "lectureStudents")
    LectureWStudentListDTO lectureToLectureWStudentListDTO(Lecture lecture);
}
