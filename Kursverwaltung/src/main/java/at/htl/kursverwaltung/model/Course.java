package at.htl.kursverwaltung.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.json.bind.annotation.JsonbTypeAdapter;
import javax.persistence.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import at.htl.kursverwaltung.core.LocalDateAdapter;
import at.htl.kursverwaltung.model.Subject;
import at.htl.kursverwaltung.model.Teacher;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NamedQuery(name = "Course.findAll", query = "select c from Course c")
@Entity(name = "Course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    private Subject subject;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    private Teacher teacher;

    @JsonbTransient
    @OneToMany(mappedBy = "course")
    private Set<Enrolment> enrolmentList;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @JsonbTypeAdapter(LocalDateAdapter.class)
    private LocalDateTime date;

    public Course(String name, Subject subject, Teacher teacher, LocalDateTime date) {
        this.name = name;
        this.subject = subject;
        this.teacher = teacher;
        this.date = date;
        this.enrolmentList = new HashSet<>();
    }

    public Course() {
        this(null, null, null, null);
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
        if(this.teacher != teacher){
            if(this.teacher != null){
                this.teacher.getCourseList().remove(this);
            }
            this.teacher = teacher;
            this.teacher.addCourse(this);
        }
    }

    public Set<Enrolment> getEnrolmentList() {
        return enrolmentList;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subject=" + subject +
                ", teacher=" + teacher +
                '}';
    }

    public void addEnrolment(Enrolment enrolment) {
        if (!this.enrolmentList.contains(enrolment)) {
            enrolmentList.add(enrolment);
            enrolment.setCourse(this);
        }
    }
}
