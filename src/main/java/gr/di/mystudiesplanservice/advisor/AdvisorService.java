package gr.di.mystudiesplanservice.advisor;

import gr.di.mystudiesplanservice.declared.Declared;
import gr.di.mystudiesplanservice.declared.DeclaredService;
import gr.di.mystudiesplanservice.passed.PassedService;
import gr.di.mystudiesplanservice.user.User;
import gr.di.mystudiesplanservice.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AdvisorService {
    @Autowired
    private AdvisorDao advisorDao;

    @Autowired
    private UserService userService;

    @Autowired
    private DeclaredService declaredService;

    @Autowired
    private PassedService passedService;

    public Advisor getAdvisorById(Long advisorId) {
        return advisorDao.read(advisorId);
    }

    public Advisor deactivate(Long userId, Long advisorId) {
        Advisor advisor = advisorDao.read(advisorId);

        advisor.setActive(false);

        return advisorDao.update(advisor);
    }

    public Advisor getActiveAdvisor(Long userId) {
        return advisorDao.findActiveAdvisor(userId);
    }

    public List<Advisor> getAdvisorsByUser(Long userId) {
        return advisorDao.findAdvisorsByUser(userId);
    }


    public Advisor changeActiveYearAndExamination(Long userId, Short year, Short examination) {
        Advisor advisor = advisorDao.findActiveAdvisor(userId);

        if (year == advisor.getActive_year() && examination == advisor.getActive_examination()) {
            if (advisor.getActive_examination() == 2) {
                advisor.setActive_year((short) (advisor.getActive_year() + 1));
                advisor.setActive_examination((short) 0);
            } else {
                advisor.setActive_examination((short) (advisor.getActive_examination() + 1));
            }

            return advisorDao.update(advisor);
        } else {
            return advisor;
        }

    }


    public Advisor create(Long userId) {
        User user = userService.getUserById(userId);

        Advisor advisorActive = getActiveAdvisor(userId);

        if (advisorActive != null) {
            deactivate(userId, advisorActive.getId());
        }

        Advisor advisor = new Advisor();

        advisor.setUser(user);
        advisor.setActive(true);
        advisor.setActive_year((short) 1);
        advisor.setActive_examination((short) 0);
        advisor.setYears((short) 4);

        return advisorDao.create(advisor);
    }

    public void delete(Long userId) {
        List<Advisor> advisors = this.getAdvisorsByUser(userId);
        Advisor advisorToDelete = getActiveAdvisor(userId);

        declaredService.deleteByAdvisorId(userId, advisorToDelete.getId());

        if (advisors.size() == 1) {
            this.create(userId);
        } else {
            advisors.remove(advisorToDelete);
            this.changeActiveAdvisor(userId, advisors.get(0).getId());
        }
        advisorDao.delete(advisorToDelete);
    }

    public Advisor addYear(Long userId) {
        Advisor advisor = advisorDao.findActiveAdvisor(userId);
        advisor.setYears((short) (advisor.getYears() + 1));

        return advisorDao.update(advisor);
    }

    public Advisor removeYear(Long userId) {
        Advisor advisor = advisorDao.findActiveAdvisor(userId);
        declaredService.removeByYear(userId, advisor.getId(), advisor.getYears());
        passedService.removeByYear(userId, advisor.getYears());

        if (advisor.getActive_year() == advisor.getYears()) {
            advisor.setActive_year((short) (advisor.getActive_year() - 1));
            advisor.setActive_examination((short) 2);
        }
        advisor.setYears((short) (advisor.getYears() - 1));

        return advisorDao.update(advisor);
    }

    public Advisor changeActiveAdvisor(Long userId, Long advisorId) {
        Advisor activeAdvisor = advisorDao.findActiveAdvisor(userId);
        activeAdvisor.setActive(false);
        advisorDao.update(activeAdvisor);

        Advisor newAdvisor = advisorDao.read(advisorId);
        newAdvisor.setActive(true);
        advisorDao.update(newAdvisor);

        return newAdvisor;
    }

    public void deleteByUser(Long id) {
        List<Advisor> advisors = advisorDao.findAdvisorsByUser(id);

        if (advisors != null || !advisors.isEmpty()) {
            for (Advisor advisor : advisors) {
                advisorDao.delete(advisor);
            }
        }
    }
}
