package pl.piasecki.javadevmanagementapp.services;

import org.springframework.stereotype.Service;
import pl.piasecki.javadevmanagementapp.api.mapper.LectureStudentMapper;
import pl.piasecki.javadevmanagementapp.api.model.LectureStudentDTO;
import pl.piasecki.javadevmanagementapp.domain.LectureStudent;
import pl.piasecki.javadevmanagementapp.repositories.LectureStudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LectureStudentServiceImpl implements LectureStudentService {

    private final LectureStudentMapper lectureStudentMapper;
    private final LectureStudentRepository lectureStudentRepository;

    public LectureStudentServiceImpl(LectureStudentMapper lectureStudentMapper, LectureStudentRepository lectureStudentRepository) {
        this.lectureStudentMapper = lectureStudentMapper;
        this.lectureStudentRepository = lectureStudentRepository;
    }

    @Override
    public List<LectureStudentDTO> getAllLectureStudents() {
        return lectureStudentRepository.findAll()
                .stream()
                .map(lectureStudentMapper::lectureStudentToLectureStudentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LectureStudentDTO insetGrade(Long lectureId, Long studentId, Double grade) {
        LectureStudent lectureStudent = lectureStudentRepository.findByLecture_IdAndStudent_Id(lectureId, studentId);
        lectureStudent.setGrade(grade);
        lectureStudentRepository.save(lectureStudent);

        return lectureStudentMapper
                .lectureStudentToLectureStudentDTO(
                        lectureStudentRepository.findByLecture_IdAndStudent_Id(lectureId, studentId));
    }
}
