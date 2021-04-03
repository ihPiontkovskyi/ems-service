package ua.knu.gra.data.course;

import lombok.Getter;
import lombok.Setter;
import ua.knu.gra.data.post.PostData;
import ua.knu.gra.data.task.TaskData;
import ua.knu.gra.data.UserData;

import java.util.Set;

@Setter
@Getter
public class CourseData {
    private String name;
    private String description;
    private UserData lecturer;
    private Set<TaskData> tasks;
    private Set<PostData> posts;
}
