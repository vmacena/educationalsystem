package view;

import model.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import repository.StudentRepository;
@SpringBootApplication
public class Test {
    public static void main(String[] args) {

        SpringApplication.run(Test.class, args);

//        Student student = new Student();
//
//        student.setName("lol");
//        student.setAcademicRegister("123");
//
//        Student student2 = new Student();
//
//        student2.setName("EADDDDDDDD");
//        student2.setAcademicRegister("123546456");
//
//        StudentRepository.saveStudent(student);
//        StudentRepository.saveStudent(student2);
//        Student newGuy = new Student();
//        newGuy.setName("Lol");
//        newGuy.setAcademicRegister("Different!!");
//
//        StudentRepository.updateStudentById(2L, newGuy);

//        StudentRepository.deleteStudentById(2L);

        System.out.println(StudentRepository.findStudentByName("lol"));

    }
}
