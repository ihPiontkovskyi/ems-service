package ua.knu.gra.data.task;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstimatingDetailsData {
    private String answer;
    private String taskName;
    private String taskSummary;
    private String course;
    private Integer maxRating;
    private Integer taskId;
}
