package model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String academicRegister;
    private String email;
    private BigDecimal grade1;
    private BigDecimal grade2;
    private BigDecimal grade3;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", academicRegister='" + academicRegister + '\'' +
                ", email='" + email + '\'' +
                ", grade1=" + grade1 +
                ", grade2=" + grade2 +
                ", grade3=" + grade3 +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getGrade1() {
        return grade1;
    }

    public void setGrade1(BigDecimal grade1) {
        this.grade1 = grade1;
    }

    public BigDecimal getGrade2() {
        return grade2;
    }

    public void setGrade2(BigDecimal grade2) {
        this.grade2 = grade2;
    }

    public BigDecimal getGrade3() {
        return grade3;
    }

    public void setGrade3(BigDecimal grade3) {
        this.grade3 = grade3;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcademicRegister() {
        return academicRegister;
    }

    public void setAcademicRegister(String academicRegister) {
        this.academicRegister = academicRegister;
    }

    public String getFinalStatus() {
        BigDecimal totalGrade = grade1.add(grade2).add(grade3);
        BigDecimal average = totalGrade.divide(new BigDecimal("3"), 2, BigDecimal.ROUND_HALF_UP);
        double averageDouble = average.doubleValue();
        long roundedAverage = Math.round(averageDouble);
        if (roundedAverage >= 6) {
            return "Aprovado\n" + "Média: " + average.toString();
        } else if (roundedAverage >= 4) {
            return "IFA\n" + "Média: " + average.toString();
        } else {
            return "Reprovado\n" + "Média: " + average.toString();
        }
    }
}
