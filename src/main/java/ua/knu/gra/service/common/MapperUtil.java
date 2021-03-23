package ua.knu.gra.service.common;

import ua.knu.gra.data.PostData;
import ua.knu.gra.data.UserData;
import ua.knu.gra.data.course.CourseData;
import ua.knu.gra.data.course.CourseMainPageData;
import ua.knu.gra.data.group.GroupData;
import ua.knu.gra.data.group.GroupMainPageData;
import ua.knu.gra.data.task.EstimatingDetailsData;
import ua.knu.gra.data.task.EstimatingListingData;
import ua.knu.gra.data.task.TaskData;
import ua.knu.gra.data.task.TaskEstimatedTableData;
import ua.knu.gra.model.*;

import java.util.Set;
import java.util.stream.Collectors;

public final class MapperUtil {
    private MapperUtil() {
    }

    public static CourseMainPageData mapToMainPageCourseData(CourseModel model) {
        CourseMainPageData data = new CourseMainPageData();
        data.setName(model.getName());
        data.setUid(model.getUid());
        data.setLecturer(model.getLecturer().getLastName() + " " + model.getLecturer().getFirstName());
        return data;
    }

    public static GroupMainPageData mapToMainPageGroupData(GroupModel model) {
        GroupMainPageData data = new GroupMainPageData();
        data.setName(model.getName());
        data.setUid(model.getUid());
        data.setLecturer(model.getLecturer().getLastName() + " " + model.getLecturer().getFirstName());
        return data;
    }

    public static TaskEstimatedTableData mapToEstimatedTaskData(RatingModel ratingModel) {
        TaskEstimatedTableData data = new TaskEstimatedTableData();
        data.setMaxRating(ratingModel.getTask().getMaxRating());
        data.setTaskName(ratingModel.getTask().getSummary());
        data.setUserData(mapToUserData(ratingModel.getUser()));
        data.setRating(ratingModel.getValue());
        return data;
    }

    public static EstimatingListingData mapToEstimatingListingData(RatingModel model) {
        EstimatingListingData estimatingListingData = new EstimatingListingData();
        estimatingListingData.setCourse(model.getCourse().getName());
        estimatingListingData.setStudent(model.getUser().getLastName() + " " + model.getUser().getFirstName());
        estimatingListingData.setTask(model.getTask().getSummary());
        return estimatingListingData;
    }

    public static EstimatingDetailsData mapToEstimatingDetails(RatingModel model) {
        EstimatingDetailsData data = new EstimatingDetailsData();
        data.setAnswer(model.getAnswer());
        data.setCourse(model.getCourse().getName());
        data.setMaxRating(model.getTask().getMaxRating());
        data.setTaskId(model.getTask().getId());
        data.setTaskName(model.getTask().getSummary());
        data.setTaskSummary(model.getTask().getSummary());
        return data;
    }

    public static CourseData mapToCourseData(CourseModel courseModel, UserModel current) {
        CourseData data = new CourseData();
        data.setName(courseModel.getName());
        data.setDescription(courseModel.getDescription());
        data.setLecturer(mapToUserData(courseModel.getLecturer()));
        data.setPosts(mapAllToPostData(courseModel.getPosts()));
        data.setTasks(mapAllToTaskData(courseModel.getTasks(), current));
        return data;
    }

    public static GroupData mapToGroupData(GroupModel courseModel) {
        GroupData data = new GroupData();
        data.setName(courseModel.getName());
        data.setDescription(courseModel.getDescription());
        data.setLecturer(mapToUserData(courseModel.getLecturer()));
        data.setPosts(mapAllToPostData(courseModel.getPosts()));
        return data;
    }

    public static UserData mapToUserData(UserModel model) {
        UserData data = new UserData();
        data.setFirstName(model.getFirstName());
        data.setLastName(model.getLastName());
        data.setUid(model.getUid());
        return data;
    }

    public static PostData mapToPostData(PostModel model) {
        PostData data = new PostData();
        data.setSummary(model.getSummary());
        data.setSequenceNumber(model.getSequenceNumber());
        data.setContent(model.getContent());
        return data;
    }

    public static Set<PostData> mapAllToPostData(Set<PostModel> modelSet) {
        return modelSet.stream()
                .map(MapperUtil::mapToPostData)
                .collect(Collectors.toSet());
    }

    public static Set<TaskData> mapAllToTaskData(Set<TaskModel> modelSet, UserModel current) {
        return modelSet.stream()
                .map(model -> mapToTaskData(model, current))
                .collect(Collectors.toSet());
    }

    public static TaskData mapToTaskData(TaskModel model, UserModel current) {
        TaskData data = new TaskData();
        data.setContent(model.getContent());
        data.setSummary(data.getSummary());
        data.setMaxRating(model.getMaxRating());
        Integer ratingValue = model.getRatings()
                .stream()
                .filter(rating -> rating.getUser().getUid().equals(current.getUid()))
                .map(RatingModel::getValue)
                .findAny()
                .orElse(0);
        data.setRating(ratingValue);
        return data;
    }

}
