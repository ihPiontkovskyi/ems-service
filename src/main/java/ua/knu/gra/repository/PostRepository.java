package ua.knu.gra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.knu.gra.model.PostModel;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<PostModel, Integer> {
    @Query(value = "select * from posts p join courses c on p.course_id = c.id where p.sequence_number = ?1 and c.uid = ?2", nativeQuery = true)
    Optional<PostModel> findBySeqNumbAndCourseUid(Integer seqNum, String uid);

    @Query(value = "select * from posts p join user_groups g on p.group_id = g.id where p.sequence_number = ?1 and g.uid = ?2", nativeQuery = true)
    Optional<PostModel> findBySeqNumbAndGroupUid(Integer seqNum, String uid);

}
