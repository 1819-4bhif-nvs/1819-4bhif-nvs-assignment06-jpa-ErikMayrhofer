package at.htl.kursverwaltung.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Subject subject;

    @ManyToOne
    private Teacher teacher;

    @OneToMany(mappedBy = "course")
    private List<Enrolment> enrolmentList = new ArrayList<Enrolment>();


    public Course(String name, Subject subject, Teacher teacher) {
        this.name = name;
        this.subject = subject;
        this.teacher = teacher;
    }

    public Course() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Enrolment> getEnrolmentList() {
        return enrolmentList;
    }

    public void setEnrolmentList(List<Enrolment> enrolmentList) {
        this.enrolmentList = enrolmentList;
    }
}