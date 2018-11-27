package at.htl.kursverwaltung.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Teacher")
public class Teacher extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String teacherNumber;

    @OneToMany(mappedBy = "teacher")
    private List<Course> courseList = new ArrayList<Course>();

    public Teacher() {
    }

    public Teacher(String firstName, String lastName, String teacherNumber) {
        super(firstName, lastName);
        this.teacherNumber = teacherNumber;
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

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
}
