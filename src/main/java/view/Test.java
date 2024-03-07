package view;

import controller.StudentManagementController;
import model.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import repository.StudentRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import static repository.StudentRepository.updateStudent;

@SpringBootApplication
public class Test {
    public static void main(String[] args) {

        SpringApplication.run(Test.class, args);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("***** CADASTRO DE ALUNOS *****");
            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Excluir aluno");
            System.out.println("3 - Alterar aluno");
            System.out.println("4 - Buscar aluno pelo nome");
            System.out.println("5 - Listar alunos (Com status de APROVAÇÃO)");
            System.out.println("6 - FIM");
            System.out.print("Digite a opção desejada: ");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("CADASTRO DE ALUNO:");
                    System.out.print("Digite o nome: ");
                    String name = scanner.next();
                    System.out.print("Digite o RA: ");
                    String ra = scanner.next();
                    System.out.print("Digite o email: ");
                    String email = scanner.next();
                    System.out.print("Digite a nota 1: ");
                    int grade1 = scanner.nextInt();
                    System.out.print("Digite a nota 2: ");
                    int grade2 = scanner.nextInt();
                    System.out.print("Digite a nota 3: ");
                    int grade3 = scanner.nextInt();

                    Student student = new Student();
                    student.setName(name);
                    student.setAcademicRegister(ra);
                    student.setEmail(email);
                    student.setGrade1(BigDecimal.valueOf(grade1));
                    student.setGrade2(BigDecimal.valueOf(grade2));
                    student.setGrade3(BigDecimal.valueOf(grade3));
                    StudentRepository.saveStudent(student);
                    break;
                case 2:
                    System.out.print("Digite o RA do aluno a ser excluído: ");
                    String raToDelete = scanner.next();
                    Student studentToDelete = StudentRepository.findByAcademicRegister(raToDelete);
                    if (studentToDelete == null) {
                        System.out.println("Nenhum aluno encontrado com o RA: " + raToDelete);
                    } else {
                        StudentRepository.deleteStudentById(studentToDelete.getId());
                    }
                    break;
                case 3:
                    System.out.println("ALTERAR ALUNO:");
                    System.out.print("Digite o nome do aluno que deseja alterar: ");
                    name = scanner.next();

                    Student existingStudent = StudentRepository.findStudentByName(name);
                    if (existingStudent == null) {
                        System.out.println("Nenhum aluno encontrado com o nome: " + name);
                        break;
                    }

                    System.out.print("Digite o novo nome do aluno: ");
                    String newName = scanner.next();

                    System.out.print("Digite o novo RA do aluno: ");
                    String newAcademicRegister = scanner.next();

                    System.out.print("Digite o novo email do aluno: ");
                    String newEmail = scanner.next();

                    System.out.print("Digite a nova nota 1 do aluno: ");
                    BigDecimal newGrade1 = scanner.nextBigDecimal();

                    System.out.print("Digite a nova nota 2 do aluno: ");
                    BigDecimal newGrade2 = scanner.nextBigDecimal();

                    System.out.print("Digite a nova nota 3 do aluno: ");
                    BigDecimal newGrade3 = scanner.nextBigDecimal();

                    StudentManagementController studentManagementController = new StudentManagementController();
                    studentManagementController.updateStudent(name, newName, newAcademicRegister, newEmail, newGrade1,
                            newGrade2, newGrade3);
                    break;
                case 4:
                    System.out.print("Digite o nome do aluno a ser buscado: ");
                    String nameToSearch = scanner.next();
                    Student foundStudent = StudentRepository.findStudentByName(nameToSearch);
                    if (foundStudent != null) {
                        System.out.println(foundStudent);
                    }
                    break;
                case 5:
                    List<Student> students = StudentRepository.findAllStudents();
                    for (Student s : students) {
                        System.out.println(s);
                    }
                    break;
                case 6:
                    System.out.println("SAIR");
                    StudentRepository.closeEntityManager();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
