package gr.di.mystudiesplanservice.advisor;

import gr.di.mystudiesplanservice.Dao;

import java.util.List;

public interface AdvisorDao extends Dao<Advisor, Long> {

    Advisor findActiveAdvisor(Long userId);

    List<Advisor> findAdvisorsByUser(Long userId);
}
