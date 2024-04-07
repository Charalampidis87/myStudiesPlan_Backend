package gr.di.mystudiesplanservice.recommended;

import gr.di.mystudiesplanservice.Identifiable;
import gr.di.mystudiesplanservice.course.Course;

import javax.persistence.*;

@Entity
@Table(name = "course_has_recommended")
public class Recommended implements Identifiable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course", referencedColumnName = "id", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "recommended", referencedColumnName = "id", nullable = false)
    private Course recommended;

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

    public Course getRecommended() {
        return recommended;
    }

    public void setRecommended(Course recommended) {
        this.recommended = recommended;
    }
}
