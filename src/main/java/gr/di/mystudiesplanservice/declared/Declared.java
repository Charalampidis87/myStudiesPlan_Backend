package gr.di.mystudiesplanservice.declared;


import gr.di.mystudiesplanservice.Identifiable;
import gr.di.mystudiesplanservice.advisor.Advisor;
import gr.di.mystudiesplanservice.course.Course;
import gr.di.mystudiesplanservice.user.User;

import javax.persistence.*;

@Entity
@Table(name = "declared")
public class Declared implements Identifiable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "course", referencedColumnName = "id", nullable = false)
    private Course course;

    @Column(name = "examination")
    private Short examination;

    @ManyToOne
    @JoinColumn(name = "advisor", referencedColumnName = "id", nullable = false)
    private Advisor advisor;

    @Column(name = "gradegoal")
    private Double gradegoal;

    @Column(name = "year")
    private Short year;

    @Column( name = "grade")
    private Double grade;

    @Column( name = "available")
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

    public Short getYear() {
        return year;
    }

    public void setYear(Short year) {
        this.year = year;
    }

    public Advisor getAdvisor() {
        return advisor;
    }

    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
    }

    public Double getGradegoal() { return gradegoal; }

    public void setGradegoal(Double gradegoal) { this.gradegoal = gradegoal; }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
