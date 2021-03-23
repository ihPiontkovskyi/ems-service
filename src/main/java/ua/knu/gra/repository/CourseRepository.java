package ua.knu.gra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.knu.gra.model.CourseModel;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<CourseModel, Integer> {
    Optional<CourseModel> findByUid(String uid);
}
