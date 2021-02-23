package ua.knu.gra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.knu.gra.model.RatingModel;

@Repository
public interface RatingRepository extends JpaRepository<RatingModel, Integer> {
}
