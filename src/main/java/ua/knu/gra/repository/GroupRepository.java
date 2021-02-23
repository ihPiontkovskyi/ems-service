package ua.knu.gra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.knu.gra.model.GroupModel;

@Repository
public interface GroupRepository extends JpaRepository<GroupModel, Integer> {
}
