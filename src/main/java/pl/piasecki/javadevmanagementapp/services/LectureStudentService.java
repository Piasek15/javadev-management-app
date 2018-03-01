package pl.piasecki.javadevmanagementapp.services;

import pl.piasecki.javadevmanagementapp.api.model.LectureStudentDTO;

import java.util.List;

public interface LectureStudentService {
    List<LectureStudentDTO> getAllLectureStudents();
    LectureStudentDTO getLectureStudent(Long lectureId, Long studentId);
    LectureStudentDTO insetGrade(Long lectureId, Long studentId, Double grade);
    LectureStudentDTO deleteGrade(Long lectureId, Long studentId);
    List<LectureStudentDTO> getLectureStudentsByGrade(Double grade);
}
