package ua.knu.gra.controller;

import org.springframework.web.bind.annotation.*;
import ua.knu.gra.data.task.*;
import ua.knu.gra.service.TaskService;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController("/webapi/tasks")
public class TaskController {

    @Resource
    private TaskService taskService;

    @GetMapping(value = "/{courseUid}/estimate-table", produces = APPLICATION_JSON_VALUE)
    public List<TaskEstimatedTableData> getEstimatedTask(@PathVariable String courseUid, @RequestParam String userUid) {
        return taskService.getForCurrent(userUid, courseUid);
    }

    @GetMapping(value = "/details", produces = APPLICATION_JSON_VALUE)
    public EstimatingDetailsData getEstimateDetails(@RequestParam Integer taskId, @RequestParam String userId) {
        return taskService.getTaskDetail(taskId, userId);
    }

    @GetMapping(value = "/{courseUid}/estimate/list", produces = APPLICATION_JSON_VALUE)
    public List<EstimatingListingData> getEstimatingList(@PathVariable String courseUid, @RequestParam String userUid) {
        return taskService.getEstimatingList(courseUid, userUid);
    }

    @PostMapping(value = "/estimate")
    public void estimate(@RequestBody EstimateData data) {
        taskService.estimateTask(data);
    }

    @PostMapping(value = "/add-task")
    public void addTask(@RequestBody TaskData data) {
        taskService.addTask(data);
    }

    @PostMapping(value = "/{courseUid}/{taskId}")
    public void writeAnswer(@PathVariable String courseUid, @PathVariable Integer taskId, @RequestParam String answer, @RequestParam String userUid) {
        taskService.writeAnswer(answer, courseUid, userUid, taskId);

    }
}
