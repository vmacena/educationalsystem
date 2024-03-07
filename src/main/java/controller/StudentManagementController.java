package controller;

import model.Student;
import repository.StudentRepository;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

public class StudentManagementController {

    public void registerStudent(String name, String academicRegister, String email,
                                BigDecimal grade1, BigDecimal grade2, BigDecimal grade3){
        Student student = createStudent(name, academicRegister, email, grade1, grade2, grade3);
        StudentRepository.saveStudent(student);
    }

    public void removeStudent(String name){
        StudentRepository.deleteStudentById(findStudent(name).getId());
    }

    public void updateStudent(String name, String newName, String academicRegister, String email,
                              BigDecimal grade1, BigDecimal grade2, BigDecimal grade3){
        Student student = createStudent(newName, academicRegister, email, grade1, grade2, grade3);
        StudentRepository.updateStudentById(findStudent(name).getId(), student);
    }

    public void findAllStudents(){
        if(StudentRepository.findAllStudents().isEmpty())
            throw new NoSuchElementException("Student list is empty.");
    }

    private Student findStudent(String name){
            Student student = StudentRepository.findStudentByName(name);
            if (student == null)
                throw new NoSuchElementException("Student not found.");
            return student;
    }

    private Student createStudent(String name, String academicRegister, String email,
                               BigDecimal grade1, BigDecimal grade2, BigDecimal grade3){
        Student student = new Student();
        student.setName(name);
        student.setAcademicRegister(academicRegister);
        student.setEmail(email);
        student.setGrade1(grade1);
        student.setGrade2(grade2);
        student.setGrade3(grade3);
        return student;
    }

    public String formatStudentInfo(Student student) {
        return "Aluno: " +
                "\nNome: " + student.getName() +
                "\nRA: " + student.getAcademicRegister() +
                "\nEmail: " + student.getEmail() +
                "\nNota 1: " + student.getGrade1() +
                "\nNota 2: " + student.getGrade2() +
                "\nNota 3: " + student.getGrade3() +
                "\nStatus de Aprovação: " + student.getFinalStatus() +
                "\n-------------------------";
    }

}
