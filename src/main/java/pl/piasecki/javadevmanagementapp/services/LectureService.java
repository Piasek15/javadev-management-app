package pl.piasecki.javadevmanagementapp.services;

import pl.piasecki.javadevmanagementapp.api.model.LectureDTO;

import java.util.List;

public interface LectureService{
    List<LectureDTO> getAllLectures();
    LectureDTO getLectureById(Long id);
    LectureDTO createNewLecture(LectureDTO lectureDTO);
    LectureDTO updateLecture(Long id, LectureDTO lectureDTO);
    void deleteLecture(Long id);
    LectureDTO addStudentToLecture(Long lectureId, Long studentId);

}
