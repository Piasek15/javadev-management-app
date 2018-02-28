package pl.piasecki.javadevmanagementapp.services;

import pl.piasecki.javadevmanagementapp.api.model.LectureStudentDTO;

import java.util.List;

public interface LectureStudentService {
    List<LectureStudentDTO> getAllLectureStudents();
    LectureStudentDTO setGrade(Double grade);
}
