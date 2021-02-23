package ua.knu.gra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.knu.gra.model.TaskModel;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, Integer> {
}
