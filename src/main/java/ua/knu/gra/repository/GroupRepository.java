package ua.knu.gra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.knu.gra.model.CourseModel;
import ua.knu.gra.model.GroupModel;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<GroupModel, Integer> {
    Optional<GroupModel> findByUid(String uid);
}
