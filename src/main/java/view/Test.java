package view;

import controller.StudentManagementController;
import model.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import repository.StudentRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static repository.StudentRepository.updateStudent;

@SpringBootApplication
public class Test {
    public static void main(String[] args) {

        SpringApplication.run(Test.class, args);
        Scanner scanner = new Scanner(System.in);
        StudentManagementController studentManagementController = new StudentManagementController();

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
                    BigDecimal grade1 = scanner.nextBigDecimal();
                    System.out.print("Digite a nota 2: ");
                    BigDecimal grade2 = scanner.nextBigDecimal();
                    System.out.print("Digite a nota 3: ");
                    BigDecimal grade3 = scanner.nextBigDecimal();

                    studentManagementController.registerStudent(name, ra, email, grade1, grade2, grade3);
                    break;
                case 2:
                    System.out.print("Digite o nome do aluno a ser excluído: ");
                    String nameToDelete = scanner.next();

                    studentManagementController.removeStudent(nameToDelete);
                    break;
                case 3:
                    System.out.println("ALTERAR ALUNO:");
                    System.out.print("Digite o nome do aluno que deseja alterar: ");
                    String oldName = scanner.next();
                    Student existingStudent = studentManagementController.findStudentQuietly(oldName);
                    if (existingStudent == null) {
                        System.out.println("===============\n");
                        System.out.println("Estudante " + oldName + " não encontrado.\n");
                        System.out.println("===============");
                        continue;
                    }
                    System.out.print("Digite o novo nome do aluno: ");
                    String newName = scanner.next();
                    System.out.print("Digite o novo RA do aluno: ");
                    String newRa = scanner.next();
                    System.out.print("Digite o novo email do aluno: ");
                    String newEmail = scanner.next();
                    System.out.print("Digite a nova nota 1 do aluno: ");
                    BigDecimal newGrade1 = scanner.nextBigDecimal();
                    System.out.print("Digite a nova nota 2 do aluno: ");
                    BigDecimal newGrade2 = scanner.nextBigDecimal();
                    System.out.print("Digite a nova nota 3 do aluno: ");
                    BigDecimal newGrade3 = scanner.nextBigDecimal();

                    studentManagementController.updateStudent(oldName, newName, newRa, newEmail, newGrade1, newGrade2, newGrade3);
                    break;
                case 4:
                    System.out.print("Digite o nome do aluno a ser buscado: ");
                    String nameToSearch = scanner.next();
                    try {
                        Student foundStudent = studentManagementController.findStudent(nameToSearch);
                        System.out.println(studentManagementController.formatStudentInfo(foundStudent));
                    } catch (NoSuchElementException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    List<Student> students = StudentRepository.findAllStudents();
                    for (Student s : students) {
                        System.out.println(studentManagementController.formatStudentInfo(s));
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