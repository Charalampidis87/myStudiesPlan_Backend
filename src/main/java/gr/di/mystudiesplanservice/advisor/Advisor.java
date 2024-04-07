package gr.di.mystudiesplanservice.advisor;

import gr.di.mystudiesplanservice.Identifiable;
import gr.di.mystudiesplanservice.user.User;

import javax.persistence.*;

@Entity
@Table(name = "advisor")
public class Advisor implements Identifiable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "active")
    private boolean active;

    @Column(name = "active_year")
    private Short active_year;

    @Column(name = "active_examination")
    private Short active_examination;

    @Column(name = "years")
    private Short years;

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Short getActive_year() {
        return active_year;
    }

    public void setActive_year(Short active_year) {
        this.active_year = active_year;
    }

    public Short getActive_examination() {
        return active_examination;
    }

    public void setActive_examination(Short active_examination) {
        this.active_examination = active_examination;
    }

    public Short getYears() {
        return years;
    }

    public void setYears(Short years) {
        this.years = years;
    }
}
