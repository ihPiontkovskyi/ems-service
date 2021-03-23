package ua.knu.gra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.knu.gra.model.RatingModel;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<RatingModel, Integer> {

    @Query(value = "select * from ratings r where r.task_id = ?1", nativeQuery = true)
    List<RatingModel> findAllByTaskById(List<Integer> taskId);
}
