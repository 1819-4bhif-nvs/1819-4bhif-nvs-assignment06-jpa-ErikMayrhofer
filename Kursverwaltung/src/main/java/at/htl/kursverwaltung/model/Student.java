package at.htl.kursverwaltung.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "Student.findAll", query = "select s from Student s"),
        @NamedQuery(name = "Student.findById", query = "select s from Student s WHERE s.id = :ID")
})
@Entity(name = "Student")
public class Student extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String matNumber;

    public Student(String firstName, String lastName, String matNumber) {
        super(firstName, lastName);
        this.matNumber = matNumber;
    }

    public Student() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getMatNumber() {
        return matNumber;
    }

    public void setMatNumber(String matNumber) {
        this.matNumber = matNumber;
    }
}
