package gr.di.mystudiesplanservice.prerequisite;

import gr.di.mystudiesplanservice.Identifiable;
import gr.di.mystudiesplanservice.course.Course;

import javax.persistence.*;

@Entity
@Table(name = "course_has_prerequisites")
public class Prerequisite implements Identifiable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course", referencedColumnName = "id", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "prerequisite", referencedColumnName = "id", nullable = false)
    private Course prerequisite;

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

    public Course getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(Course prerequisite) {
        this.prerequisite = prerequisite;
    }
}
