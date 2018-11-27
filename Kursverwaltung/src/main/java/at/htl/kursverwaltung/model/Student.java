package at.htl.kursverwaltung.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "Student.findAll", query = "select s from Student s INNER JOIN FETCH s.enrolmentList"),
        @NamedQuery(name = "Student.findById", query = "select s from Student s INNER JOIN FETCH s.enrolmentList where s.id = :ID")
})
@Entity(name = "Student")
public class Student extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String matNumber;

    @OneToMany(mappedBy = "student")
    private List<Enrolment> enrolmentList = new ArrayList<Enrolment>();

    public Student(String firstName, String lastName, String matNumber) {
        super(firstName, lastName);
        this.matNumber = matNumber;
    }

    public Student() {
    }

    public Enrolment enrol(Course course){
        Enrolment enrolment = new Enrolment(course, this);
        course.getEnrolmentList().add(enrolment);
        getEnrolmentList().add(enrolment);
        return enrolment;
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

    public List<Enrolment> getEnrolmentList() {
        return enrolmentList;
    }

    public void setEnrolmentList(List<Enrolment> enrolmentList) {
        this.enrolmentList = enrolmentList;
    }
}
