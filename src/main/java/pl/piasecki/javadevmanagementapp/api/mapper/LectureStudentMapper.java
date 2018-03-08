package pl.piasecki.javadevmanagementapp.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.piasecki.javadevmanagementapp.api.model.LSLectureAndGradeDTO;
import pl.piasecki.javadevmanagementapp.api.model.LSLectureDTO;
import pl.piasecki.javadevmanagementapp.api.model.LSStudentDTO;
import pl.piasecki.javadevmanagementapp.api.model.LectureStudentDTO;
import pl.piasecki.javadevmanagementapp.domain.LectureStudent;

@Mapper
public interface LectureStudentMapper {
    LectureStudentMapper INSTANCE = Mappers.getMapper(LectureStudentMapper.class);
    LectureStudentDTO lectureStudentToLectureStudentDTO(LectureStudent lectureStudent);
    LectureStudent lectureStudentDTOToLectureStudent(LectureStudentDTO lectureStudentDTO);
    LSStudentDTO lectureStudentToLSStudentDTO(LectureStudent lectureStudent);
    LSLectureDTO lectureStudentToLSLectureDTO(LectureStudent lectureStudent);
    LSLectureAndGradeDTO lectureStudentToLSLectureAndGradeDTO(LectureStudent lectureStudent);
}
