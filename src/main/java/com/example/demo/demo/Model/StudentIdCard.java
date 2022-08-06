package com.example.demo.demo.Model;


import javax.persistence.*;

@Entity(name = "Student_Id_Card")
@Table(name = "student_id_card")
public class StudentIdCard {

    @Id
    @SequenceGenerator(name = "student_card_id_generator", sequenceName = "student_card_id_generator", initialValue = 200)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_card_id_generator")
    private Long id;
    @Column(name = "card_number", columnDefinition = "TEXT", nullable = false, length = 15)
    private String cardNumber;


    @OneToOne()
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "student_id_fk"
            )

    )
    private Student student;


    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public StudentIdCard( String cardNumber, Student student) {
        this.cardNumber = cardNumber;
        this.student = student;
    }

    public StudentIdCard () {}

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
