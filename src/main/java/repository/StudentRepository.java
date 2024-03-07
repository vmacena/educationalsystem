package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import model.Student;

import java.util.List;

public class StudentRepository {

    private static EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("students");
    private static EntityManager entityManager = entityManagerFactory.createEntityManager();

    public static void saveStudent(Student student){
        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
    }

    public static Student findByAcademicRegister(String academicRegister) {
        try {
            return (Student) entityManager.createQuery("SELECT s FROM Student s WHERE s.academicRegister = :ra")
                    .setParameter("ra", academicRegister)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public static Student findStudentById(Long id){
        return entityManager.find(Student.class, id);
    }

    public static Student findStudentByName(String name) {
        try {
            return (Student) entityManager.createQuery("SELECT s FROM Student s WHERE s.name = :n")
                    .setParameter("n", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public static List<Student> findAllStudents(){
        String jpql = "SELECT s FROM Student s";
        return entityManager.createQuery(jpql, Student.class).getResultList();
    }

    public static void updateStudentById(Long id, Student newStudent){
        entityManager.getTransaction().begin();
        Student student = entityManager.find(Student.class, id);
        student = newStudent;
        student.setId(id);
        entityManager.merge(student);
        entityManager.getTransaction().commit();
    }

    public static void updateStudent(Student student){
        updateStudentById(student.getId(), student);
    }

    public static void deleteStudentById(Long id){
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Student.class, id));
        entityManager.getTransaction().commit();
    }

    public static void closeEntityManager(){
        entityManager.close();
    }

}
