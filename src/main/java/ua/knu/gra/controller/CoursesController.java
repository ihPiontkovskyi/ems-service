package ua.knu.gra.controller;

import org.springframework.web.bind.annotation.*;
import ua.knu.gra.data.UserData;
import ua.knu.gra.data.course.CourseAddData;
import ua.knu.gra.data.course.CourseData;
import ua.knu.gra.data.course.CourseMainPageData;
import ua.knu.gra.data.course.PrefData;
import ua.knu.gra.service.CourseService;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController("/api/courses")
public class CoursesController {

    @Resource
    private CourseService courseService;

    @GetMapping(value = "/all-courses", produces = APPLICATION_JSON_VALUE)
    public List<CourseMainPageData> getAllCourses() {
        return courseService.getAll();
    }

    @GetMapping(value = "/{courseUid}/details", produces = APPLICATION_JSON_VALUE)
    public CourseData getCourseDetails(@PathVariable String courseUid, @RequestParam String userUid) {
        return courseService.getByUid(courseUid, userUid);
    }

    @GetMapping(value = "/{courseUid}/list-user", produces = APPLICATION_JSON_VALUE)
    public List<UserData> listUserData(@PathVariable String courseUid) {
        return courseService.getUserListing(courseUid);
    }

    @PostMapping("/add")
    public void addCourse(@RequestBody CourseAddData data, @RequestParam String userUid) {
        courseService.addCourse(data, userUid);
    }

    @PostMapping("/{courseUid}/join")
    public void joinCourse(@PathVariable String courseUid, @RequestParam String userUid) {
        courseService.joinCourse(courseUid, userUid);
    }

    @DeleteMapping("/{courseUid}/delete")
    public void delete(@PathVariable String courseUid) {
        courseService.deleteByUid(courseUid);
    }

    @PostMapping("/group-prefereces")
    public void preferUser(@RequestBody List<PrefData> prefData, @RequestParam String userUid) {
        courseService.addUserPreferences(prefData, userUid);
    }

    @PostMapping("/generate-groups")
    public void generateGroups(@RequestParam Integer numOfGroups, List<String> uid) {

    }

}
