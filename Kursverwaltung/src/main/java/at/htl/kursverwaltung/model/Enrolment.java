package at.htl.kursverwaltung.model;

import at.htl.kursverwaltung.core.LocalDateAdapter;

import javax.persistence.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@Entity(name = "Enrolment")
public class Enrolment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne()
    private Course course;

    @ManyToOne()
    private Student student;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDateTime date;

    public Enrolment() {
    }

    public Enrolment(Course course, Student student, LocalDateTime date) {
        this.course = course;
        this.student = student;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
