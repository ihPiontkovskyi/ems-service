package ua.knu.gra.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.knu.gra.data.task.*;
import ua.knu.gra.model.RatingModel;
import ua.knu.gra.model.TaskModel;
import ua.knu.gra.model.UserModel;
import ua.knu.gra.repository.CourseRepository;
import ua.knu.gra.repository.RatingRepository;
import ua.knu.gra.repository.TaskRepository;
import ua.knu.gra.repository.UserRepository;
import ua.knu.gra.service.common.MapperUtil;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;
import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TaskService {

    private final TaskRepository taskRepository;
    private final RatingRepository ratingRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public List<TaskEstimatedTableData> getForCurrent(String currentUserId, String courseUid) {
        return ratingRepository.findAll()
                .stream()
                .filter(rating -> rating.getUser().getUid().equals(currentUserId))
                .filter(rating -> rating.getCourse().getUid().equals(courseUid))
                .map(MapperUtil::mapToEstimatedTaskData)
                .collect(Collectors.toList());
    }

    public void addTask(TaskData data) {
        TaskModel task = new TaskModel();
        task.setContent(data.getContent());
        task.setSummary(data.getSummary());
        task.setMaxRating(data.getMaxRating());
        taskRepository.save(task);
    }

    public List<EstimatingListingData> getEstimatingList(String courseUid, String userUid) {
        UserModel current = userRepository.findUserModelByUid(userUid).orElseThrow(() -> new RuntimeException("Invalid uid"));
        List<Integer> taskId = taskRepository.findAll()
                .stream()
                .filter(task -> task.getCourse().getLecturer().getUid().equals(current.getUid()))
                .map(TaskModel::getId)
                .collect(Collectors.toList());
        return ratingRepository.findAllByTaskById(taskId)
                .stream()
                .filter(rating -> nonNull(rating.getAnswer()))
                .map(MapperUtil::mapToEstimatingListingData)
                .collect(Collectors.toList());
    }

    public void estimateTask(EstimateData data) {
        TaskModel task = taskRepository.findById(data.getTaskId()).orElseThrow(() -> new RuntimeException("Invalid task id"));
        RatingModel rating = task.getRatings().stream()
                .filter(ratingModel -> ratingModel.getUser().getUid().equals(data.getUserUid()))
                .findFirst().orElseThrow(() -> new RuntimeException("No answer for task"));
        rating.setValue(data.getValue());
        ratingRepository.save(rating);
    }

    public EstimatingDetailsData getTaskDetail(Integer taskId, String userUid) {
        RatingModel model = ratingRepository.findAllByTaskById(singletonList(taskId))
                .stream()
                .filter(rating -> rating.getUser().getUid().equals(userUid))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No answer for task"));
        return MapperUtil.mapToEstimatingDetails(model);
    }

    public void writeAnswer(String answer, String courseUid, String userUid, Integer taskId) {
        UserModel current = userRepository.findUserModelByUid(userUid).orElseThrow(() -> new RuntimeException("Invalid uid"));
        RatingModel model = new RatingModel();
        model.setAnswer(answer);
        model.setUser(current);
        model.setTask(taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("No task")));
        model.setCourse(courseRepository.findByUid(courseUid).orElseThrow(() -> new RuntimeException("No course")));
        ratingRepository.save(model);
    }
}
