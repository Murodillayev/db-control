package uz.pdp.dbcontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.dbcontrol.model.entity.ProjectAgent;

public interface ProjectAgentRepository extends JpaRepository<ProjectAgent,String> {
}
