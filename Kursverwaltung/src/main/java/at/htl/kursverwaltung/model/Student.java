package at.htl.kursverwaltung.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NamedQuery(name = "Student.findAll", query = "select s from Student s")
@NamedQuery(name = "Student.findById", query = "select s from Student s WHERE s.id = :ID")
@Entity(name = "Student")
public class Student extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String matNumber;

    @JsonbTransient
    @OneToMany(mappedBy = "student")
    private Set<Enrolment> enrolmentList;

    public Student(String firstName, String lastName, String matNumber) {
        super(firstName, lastName);
        this.matNumber = matNumber;
        this.enrolmentList = new HashSet<>();
    }

    public Student() {
        this(null, null, null);
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

    public Set<Enrolment> getEnrolmentList() {
        return enrolmentList;
    }

    public Enrolment[] enrol(Course... courses){
        Enrolment[] ret = new Enrolment[courses.length];
        for(int i = 0; i < courses.length; i++){
            ret[i] = new Enrolment(courses[i], this, LocalDateTime.now());
        }
        return ret;
    }

    public void addEnrolment(Enrolment enrolment) {
        if (!enrolmentList.contains(enrolment)) {
            enrolmentList.add(enrolment);
            enrolment.setStudent(this);
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", matNumber='" + matNumber + '\'' +
                ", enrolmentList=" + enrolmentList +
                '}';
    }
}
