package at.htl.kursverwaltung.model;

import at.htl.kursverwaltung.core.LocalDateAdapter;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbTypeAdapter;
import javax.persistence.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"course_id", "student_id"})
)
@NamedQuery(name = "Enrolment.findAll", query = "select e from Enrolment e")
@Entity(name = "Enrolment")
public class Enrolment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "course_id")
    @ManyToOne(cascade = {CascadeType.REFRESH})
    private Course course;

    @JoinColumn(name = "student_id")
    @ManyToOne(cascade = {CascadeType.REFRESH})
    private Student student;

    @JsonbTypeAdapter(LocalDateAdapter.class)
    private LocalDateTime date;

    public Enrolment() {
        this(null, null, null);
    }

    public Enrolment(Course course, Student student, LocalDateTime date) {
        this.course = course;
        this.student = student;
        this.date = date;
        if(student != null){
            student.addEnrolment(this);
        }
        if(course != null){
            course.addEnrolment(this);
        }
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
        if(course != this.course){
            if(this.course != null){
                course.getEnrolmentList().remove(this);
            }
            this.course = course;
            course.addEnrolment(this);
        }
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        if(student != this.student){
            if(this.student != null){
                student.getEnrolmentList().remove(this);
            }
            this.student = student;
            student.addEnrolment(this);
        }
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
