package gr.di.mystudiesplanservice.passed;

import gr.di.mystudiesplanservice.Identifiable;
import gr.di.mystudiesplanservice.course.Course;
import gr.di.mystudiesplanservice.user.User;

import javax.persistence.*;

@Entity
@Table(name = "course_is_passed")
public class Passed implements Identifiable<Long> {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="user", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="course", referencedColumnName = "id", nullable = false)
    private Course course;

    @Column(name = "examination")
    private Short examination;

    @Column(name = "grade")
    private Double grade;

    @Column(name = "year")
    private Short year;

    @Column(name = "available")
    private boolean available;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Short getExamination() {
        return examination;
    }

    public void setExamination(Short examination) {
        this.examination = examination;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public Short getYear() {
        return year;
    }

    public void setYear(Short year) {
        this.year = year;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
