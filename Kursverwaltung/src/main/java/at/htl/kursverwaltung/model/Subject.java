package at.htl.kursverwaltung.model;

import javax.persistence.*;

@NamedQuery(name = "Subject.findAll", query = "select s from Subject s")
@Entity(name = "Subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    public Subject() {
        this(null);
    }

    public Subject(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
