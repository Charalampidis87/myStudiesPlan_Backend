package gr.di.mystudiesplanservice.labprofessor;

import gr.di.mystudiesplanservice.Identifiable;
import gr.di.mystudiesplanservice.course.Course;

import javax.persistence.*;

@Entity
@Table(name = "course_has_lab_professor")
public class LabProfessor implements Identifiable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course", referencedColumnName = "id", nullable = false)
    private Course course;

    @Column(name = "lab_professor")
    private String labProfessor;

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

    public String getLabProfessor() {
        return labProfessor;
    }

    public void setLabProfessor(String labProfessor) {
        this.labProfessor = labProfessor;
    }
}
