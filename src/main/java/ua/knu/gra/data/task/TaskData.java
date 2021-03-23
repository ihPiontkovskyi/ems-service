package ua.knu.gra.data.task;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TaskData {
    private String summary;
    private String content;
    private Integer rating;
    private Integer maxRating;
}
