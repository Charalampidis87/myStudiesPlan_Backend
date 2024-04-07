package gr.di.mystudiesplanservice.specialization;

import gr.di.mystudiesplanservice.Identifiable;
import gr.di.mystudiesplanservice.course.Course;

import javax.persistence.*;

@Entity
@Table(name = "course_has_specialization")
public class Specialization implements Identifiable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course", referencedColumnName = "id", nullable = false)
    private Course course;

    @Column(name = "specialization")
    private Short specialization;

    @Column(name="type")
    private String type;

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

    public Short getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Short specialization) {
        this.specialization = specialization;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
