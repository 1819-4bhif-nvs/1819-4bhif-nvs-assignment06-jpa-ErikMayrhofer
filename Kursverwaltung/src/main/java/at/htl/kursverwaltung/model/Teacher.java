package at.htl.kursverwaltung.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NamedQuery(name = "Teacher.findAll", query = "select t from Teacher t")
@Entity(name = "Teacher")
public class Teacher extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String teacherNumber;

    @JsonbTransient
    @OneToMany(mappedBy = "teacher")
    private Set<Course> courseList;

    public Teacher() {
        this(null, null, null);
    }

    public Teacher(String firstName, String lastName, String teacherNumber) {
        super(firstName, lastName);
        this.teacherNumber = teacherNumber;
        this.courseList = new HashSet<>();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber;
    }

    public Set<Course> getCourseList() {
        return courseList;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", teacherNumber='" + teacherNumber + '\'' +
                '}';
    }

    public void addCourse(Course course) {
        if(!courseList.contains(course)){
            courseList.add(course);
            course.setTeacher(this);
        }
    }
}
