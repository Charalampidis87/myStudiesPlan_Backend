package gr.di.mystudiesplanservice.course;

import gr.di.mystudiesplanservice.Identifiable;

import javax.persistence.*;

@Entity
@Table(name = "course")
public class Course implements Identifiable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "ects")
    private Short ects;

    @Column(name = "semester")
    private Short semester;

    @Column(name = "direction")
    private String direction;

    @Column(name = "type")
    private String type;

    @Column(name = "theory")
    private Short theory;

    @Column(name = "extra")
    private Short extra;

    @Column(name = "lab")
    private Short lab;

    @Column(name = "double_exam")
    private boolean double_exam;

    @Column(name = "offered")
    private boolean offered;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Short getEcts() {
        return ects;
    }

    public void setEcts(Short ects) {
        this.ects = ects;
    }

    public Short getSemester() {
        return semester;
    }

    public void setSemester(Short semester) {
        this.semester = semester;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Short getTheory() {
        return theory;
    }

    public void setTheory(Short theory) {
        this.theory = theory;
    }

    public Short getExtra() {
        return extra;
    }

    public void setExtra(Short extra) {
        this.extra = extra;
    }

    public Short getLab() {
        return lab;
    }

    public void setLab(Short lab) {
        this.lab = lab;
    }

    public boolean isDouble_exam() {
        return double_exam;
    }

    public void setDouble_exam(boolean double_exam) {
        this.double_exam = double_exam;
    }

    public boolean isOffered() {
        return offered;
    }

    public void setOffered(boolean offered) {
        this.offered = offered;
    }
}
