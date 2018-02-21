package pl.piasecki.javadevmanagementapp.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.piasecki.javadevmanagementapp.api.model.LectureDTO;
import pl.piasecki.javadevmanagementapp.api.model.LectureWStudentListDTO;
import pl.piasecki.javadevmanagementapp.domain.Lecture;

@Mapper
public interface LectureMapper {
    LectureMapper INSTANCE = Mappers.getMapper(LectureMapper.class);
    LectureDTO lectureToLectureDTO(Lecture lecture);
    Lecture lectureDTOToLecture(LectureDTO lectureDTO);
    LectureWStudentListDTO lectureToLectureWStudentListDTO(Lecture lecture);
    Lecture lectureWStudnetListDTOToLecture(LectureWStudentListDTO lectureWStudentListDTO);
}
