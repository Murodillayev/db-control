package uz.pdp.dbcontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.dbcontrol.model.entity.ProjectAgent;

import java.util.Optional;

public interface ProjectAgentRepository extends JpaRepository<ProjectAgent, String> {

    Optional<ProjectAgent> findByCallbackUrl(String callbackUrl);
}
