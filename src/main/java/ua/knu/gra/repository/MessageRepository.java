package ua.knu.gra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.knu.gra.model.MessageModel;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageModel, Integer> {
    @Query(value = "select * from messages m join user_groups g on m.group_id = g.id where g.uid = ?1", nativeQuery = true)
    List<MessageModel> findAllByGroupUId(String groupUid);
}