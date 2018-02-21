package pl.piasecki.javadevmanagementapp.services;

import pl.piasecki.javadevmanagementapp.api.model.LectureDTO;
import pl.piasecki.javadevmanagementapp.api.model.LectureWStudentListDTO;
import pl.piasecki.javadevmanagementapp.api.model.StudentDTO;

import java.util.List;

public interface LectureService{
    List<LectureDTO> getAllLectures();
    LectureWStudentListDTO getLectureById(Long id);
    LectureDTO createNewLecture(LectureDTO lectureDTO);
    LectureDTO updateLecture(Long id, LectureDTO lectureDTO);
    void deleteLecture(Long id);
    LectureWStudentListDTO addStudentToLecture(Long lectureId, Long studentId);
    LectureWStudentListDTO deleteStudentFromLecture(Long lectureId, Long studentId);
    List<StudentDTO> getLectureStudents(Long lectureId);
}
