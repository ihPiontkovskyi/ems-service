package ua.knu.gra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.knu.gra.model.CourseModel;

@Repository
public interface CourseRepository extends JpaRepository<CourseModel, Integer> {
}
