package ua.knu.gra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.knu.gra.model.DocumentModel;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentModel, Integer> {
}
