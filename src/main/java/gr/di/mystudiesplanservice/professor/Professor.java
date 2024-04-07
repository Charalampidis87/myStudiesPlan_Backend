package gr.di.mystudiesplanservice.professor;

import gr.di.mystudiesplanservice.Identifiable;
import gr.di.mystudiesplanservice.course.Course;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "course_has_professor")
public class Professor implements Identifiable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course", referencedColumnName = "id", nullable = false)
    private Course course;

    @Column(name = "professor")
    private String professor;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }
}
