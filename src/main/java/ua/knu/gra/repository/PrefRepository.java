package ua.knu.gra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.knu.gra.model.GroupPreferencesModel;

@Repository
public interface PrefRepository extends JpaRepository<GroupPreferencesModel, Integer> {
}
