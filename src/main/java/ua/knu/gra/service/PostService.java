package ua.knu.gra.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.knu.gra.data.post.PostAddData;
import ua.knu.gra.model.PostModel;
import ua.knu.gra.repository.CourseRepository;
import ua.knu.gra.repository.GroupRepository;
import ua.knu.gra.repository.PostRepository;

@Service

@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PostService {
    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;
    private final PostRepository postRepository;

    public void addPostToGroup(String groupUid, PostAddData data) {
        PostModel model = new PostModel();
        model.setContent(data.getContent());
        model.setSummary(data.getSummary());
        model.setGroup(groupRepository.findByUid(groupUid).get());
        postRepository.save(model);
    }

    public void addPostToCourse(String courseUid, PostAddData data) {
        PostModel model = new PostModel();
        model.setContent(data.getContent());
        model.setSummary(data.getSummary());
        model.setCourse(courseRepository.findByUid(courseUid).get());
        postRepository.save(model);
    }

    public void deleteForGroup(String groupUid, Integer seqNumb) {
        PostModel model = postRepository.findBySeqNumbAndGroupUid(seqNumb, groupUid).get();
        postRepository.delete(model);
    }

    public void deleteForCourse(String courseUid, Integer seqNumb) {
        PostModel model = postRepository.findBySeqNumbAndCourseUid(seqNumb, courseUid).get();
        postRepository.delete(model);
    }
}
