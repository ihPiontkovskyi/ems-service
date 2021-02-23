package ua.knu.gra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.knu.gra.model.PostModel;

@Repository
public interface PostRepository extends JpaRepository<PostModel, Integer> {
}