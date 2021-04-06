package ua.knu.gra.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.knu.gra.model.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SQLNativeJPAIntegrationTest {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private TaskRepository taskRepository;

    private final UserModel user = new UserModel();
    private final GroupModel group = new GroupModel();
    private final MessageModel message = new MessageModel();
    private final CourseModel course = new CourseModel();
    private final PostModel coursePost = new PostModel();
    private final PostModel groupPost = new PostModel();
    private final TaskModel task = new TaskModel();
    private final RatingModel rating = new RatingModel();

    @Before
    public void setUp() {
        user.setPassword("pass");
        user.setUid("uid");
        user.setRole(UserRole.LECTURER);
        user.setLastName("lastName");
        user.setFirstName("firstName");

        group.setDescription("Description");
        group.setLecturer(new UserModel());
        group.setName("group");
        group.setUid("uidX");
        group.setLecturer(user);

        message.setGroup(group);
        message.setOwner(user);
        message.setContent("content");
        message.setSequenceNumber(1);


        course.setDescription("Description");
        course.setName("course");
        course.setUid("uidY");
        course.setLecturer(user);

        coursePost.setCourse(course);
        coursePost.setSummary("Summary_post1");
        coursePost.setContent("Content_post1");
        coursePost.setSequenceNumber(1);

        groupPost.setGroup(group);
        groupPost.setSummary("Summary_post1");
        groupPost.setContent("Content_post1");
        groupPost.setSequenceNumber(1);

        task.setMaxRating(20);
        task.setSummary("summary");
        task.setContent("content");
        task.setCourse(course);

        rating.setCourse(course);
        rating.setTask(task);
        rating.setUser(user);
        rating.setAnswer("asnwer-link");
        rating.setValue(18);
    }

    @Test
    public void testNativeQueryInMessageRepository() {
        userRepository.save(user);
        groupRepository.save(group);
        messageRepository.save(message);

        List<MessageModel> messages = messageRepository.findAllByGroupUId("uidX");

        assertThat(messages, hasSize(1));
        assertThat(messages.get(0).getContent(), equalTo("content"));
        assertThat(messages.get(0).getSequenceNumber(), equalTo(1));
    }

    @Test
    public void testNativeQueryInPostRepositoryGroupCase() {
        userRepository.save(user);
        groupRepository.save(group);
        postRepository.save(groupPost);

        Optional<PostModel> post = postRepository.findBySeqNumbAndGroupUid(1, "uidX");

        assertTrue(post.isPresent());
        assertThat(post.get().getContent(), equalTo("Content_post1"));
        assertThat(post.get().getSummary(), equalTo("Summary_post1"));
    }

    @Test
    public void testNativeQueryInPostRepositoryCourseCase() {
        userRepository.save(user);
        courseRepository.save(course);
        postRepository.save(coursePost);

        Optional<PostModel> post = postRepository.findBySeqNumbAndCourseUid(1, "uidY");

        assertTrue(post.isPresent());
        assertThat(post.get().getContent(), equalTo("Content_post1"));
        assertThat(post.get().getSummary(), equalTo("Summary_post1"));
    }

    @Test
    public void testNativeQueryInRatingRepository() {
        userRepository.save(user);
        courseRepository.save(course);
        taskRepository.save(task);
        ratingRepository.save(rating);

        List<RatingModel> allByTaskById = ratingRepository.findAllByTaskById(Collections.singletonList(task.getId()));
        assertThat(allByTaskById, hasSize(1));
    }
}