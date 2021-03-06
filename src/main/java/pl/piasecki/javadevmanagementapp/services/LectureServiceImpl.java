package pl.piasecki.javadevmanagementapp.services;

import org.springframework.stereotype.Service;
import pl.piasecki.javadevmanagementapp.api.mapper.LectureMapper;
import pl.piasecki.javadevmanagementapp.api.mapper.StudentMapper;
import pl.piasecki.javadevmanagementapp.api.model.LectureDTO;
import pl.piasecki.javadevmanagementapp.api.model.LectureWStudentListDTO;
import pl.piasecki.javadevmanagementapp.api.model.StudentDTO;
import pl.piasecki.javadevmanagementapp.domain.Lecture;
import pl.piasecki.javadevmanagementapp.domain.Student;
import pl.piasecki.javadevmanagementapp.repositories.LectureRepository;
import pl.piasecki.javadevmanagementapp.repositories.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LectureServiceImpl implements LectureService {

    private final LectureMapper lectureMapper;
    private final StudentMapper studentMapper;
    private final LectureRepository lectureRepository;
    private final StudentRepository studentRepository;

    public LectureServiceImpl(LectureMapper lectureMapper, StudentMapper studentMapper, LectureRepository lectureRepository, StudentRepository studentRepository) {
        this.lectureMapper = lectureMapper;
        this.studentMapper = studentMapper;
        this.lectureRepository = lectureRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<LectureDTO> getAllLectures() {
        return lectureRepository.findAll()
                .stream()
                .map(lectureMapper::lectureToLectureDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LectureWStudentListDTO getLectureById(Long id) {
        return lectureRepository.findById(id)
                .map(lectureMapper::lectureToLectureWStudentListDTO)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public LectureDTO createNewLecture(LectureDTO lectureDTO) {
        Lecture lecture = lectureMapper.lectureDTOToLecture(lectureDTO);
        lectureRepository.save(lecture);
        return lectureMapper.lectureToLectureDTO(lecture);
    }

    @Override
    public LectureDTO updateLecture(Long id, LectureDTO lectureDTO) {
        Lecture lecture = lectureMapper.lectureDTOToLecture(lectureDTO);
        lecture.setId(id);
        lectureRepository.save(lecture);
        return lectureMapper.lectureToLectureDTO(lecture);
    }

    @Override
    public void deleteLecture(Long id) {
        lectureRepository.deleteById(id);
    }

    @Override
    public LectureWStudentListDTO addStudentToLecture(Long lectureId, Long studentId) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(RuntimeException::new);

        Student student = studentRepository.findById(studentId)
                .orElseThrow(RuntimeException::new);

        lecture.addStudent(student);
        lectureRepository.save(lecture);
        return lectureMapper.lectureToLectureWStudentListDTO(lecture);
    }

    @Override
    public LectureWStudentListDTO deleteStudentFromLecture(Long lectureId, Long studentId) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(RuntimeException::new);

        Student student = studentRepository.findById(studentId)
                .orElseThrow(RuntimeException::new);

        lecture.deleteStudent(student);
        lectureRepository.save(lecture);
        return lectureMapper.lectureToLectureWStudentListDTO(lecture);
    }

    @Override
    public List<StudentDTO> getLectureStudents(Long lectureId) {
        return lectureRepository.findById(lectureId)
                .orElseThrow(RuntimeException::new)
                .getStudents()
                .stream()
                .map(studentMapper::studentToStudentDTO)
                .collect(Collectors.toList());
    }
}
