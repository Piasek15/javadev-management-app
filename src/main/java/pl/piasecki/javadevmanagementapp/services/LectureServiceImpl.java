package pl.piasecki.javadevmanagementapp.services;

import org.springframework.stereotype.Service;
import pl.piasecki.javadevmanagementapp.api.mapper.LectureMapper;
import pl.piasecki.javadevmanagementapp.api.model.LectureDTO;
import pl.piasecki.javadevmanagementapp.domain.Lecture;
import pl.piasecki.javadevmanagementapp.repositories.LectureRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LectureServiceImpl implements LectureService {

    private final LectureMapper lectureMapper;
    private final LectureRepository lectureRepository;

    public LectureServiceImpl(LectureMapper lectureMapper, LectureRepository lectureRepository) {
        this.lectureMapper = lectureMapper;
        this.lectureRepository = lectureRepository;
    }

    @Override
    public List<LectureDTO> getAllLectures() {
        return lectureRepository.findAll()
                .stream()
                .map(lectureMapper::lectureToLectureDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LectureDTO getLectureById(Long id) {
        return lectureRepository.findById(id)
                .map(lectureMapper::lectureToLectureDTO)
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
}
