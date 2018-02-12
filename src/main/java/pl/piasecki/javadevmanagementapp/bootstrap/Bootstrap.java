package pl.piasecki.javadevmanagementapp.bootstrap;

import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.piasecki.javadevmanagementapp.domain.Lecture;
import pl.piasecki.javadevmanagementapp.domain.Student;
import pl.piasecki.javadevmanagementapp.repositories.LectureRepository;
import pl.piasecki.javadevmanagementapp.repositories.StudentRepository;

import java.util.Date;

@Log
@Component
public class Bootstrap implements CommandLineRunner {

    private StudentRepository studentRepository;
    private LectureRepository lectureRepository;

    public Bootstrap(StudentRepository studentRepository, LectureRepository lectureRepository) {
        this.studentRepository = studentRepository;
        this.lectureRepository = lectureRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadStudents();
        loadLectures();
    }

    private void loadStudents(){
        Student student1 = new Student();
        student1.setFirstName("Frodo");
        student1.setLastName("Baggins");
        student1.setEmail("frodo@shire.me");

        Student student2 = new Student();
        student2.setFirstName("Samwise");
        student2.setLastName("Gamgee");
        student2.setEmail("sam@shire.me");

        Student student3 = new Student();
        student3.setFirstName("Legolas");
        student3.setLastName("son of Thranduil");
        student3.setEmail("legolas@ithilien.me");

        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);

        log.info("Students loaded: " + studentRepository.count());
    }

    private void loadLectures(){
        //Date format (years + 1900, month + 1, day)
        Lecture lecture1 = new Lecture();
        lecture1.setTopic("Java Basic");
        lecture1.setLocalization("Rzeszow WSIZ");
        lecture1.setDate(new Date(117, 9, 10));

        Lecture lecture2 = new Lecture();
        lecture2.setTopic("Spring Framework");
        lecture2.setLocalization("Rzeszow WSIZ");
        lecture2.setDate(new Date(117, 9, 20));

        Lecture lecture3 = new Lecture();
        lecture3.setTopic("Docker");
        lecture3.setLocalization("Online");
        lecture3.setDate(new Date(117, 12, 1));

        lectureRepository.save(lecture1);
        lectureRepository.save(lecture2);
        lectureRepository.save(lecture3);

        log.info("Lectures loaded: " + lectureRepository.count());
    }
}
