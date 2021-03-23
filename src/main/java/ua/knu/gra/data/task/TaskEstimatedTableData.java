package ua.knu.gra.data.task;

import lombok.Getter;
import lombok.Setter;
import ua.knu.gra.data.UserData;

@Setter
@Getter
public class TaskEstimatedTableData {
    private String taskName;
    private Integer rating;
    private Integer maxRating;
    private UserData userData;
}
