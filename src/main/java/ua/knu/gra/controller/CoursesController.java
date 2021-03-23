package ua.knu.gra.controller;

import org.springframework.web.bind.annotation.*;
import ua.knu.gra.data.PrefData;
import ua.knu.gra.data.UserData;
import ua.knu.gra.data.course.CourseAddData;
import ua.knu.gra.data.course.CourseData;
import ua.knu.gra.data.course.CourseMainPageData;
import ua.knu.gra.model.UserModel;
import ua.knu.gra.service.CourseService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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
    public CourseData getCourseDetails(@PathVariable String courseUid, HttpSession session) {
        UserModel userModel = (UserModel) session.getAttribute("userModel");
        return courseService.getByUid(courseUid, userModel);
    }

    @GetMapping(value = "/{courseUid}/list-user", produces = APPLICATION_JSON_VALUE)
    public List<UserData> listUserData(@PathVariable String courseUid) {
        return courseService.getUserListing(courseUid);
    }

    @PostMapping("/add")
    public void addCourse(@RequestBody CourseAddData data, HttpSession session) {
        UserModel userModel = (UserModel) session.getAttribute("userModel");
        courseService.addCourse(data, userModel);
    }

    @PostMapping("/{courseUid}/join")
    public void joinCourse(@PathVariable String courseUid, HttpSession session) {
        UserModel userModel = (UserModel) session.getAttribute("userModel");
        courseService.joinCourse(courseUid, userModel);
    }

    @DeleteMapping("/{courseUid}/delete")
    public void delete(@PathVariable String courseUid) {
        courseService.deleteByUid(courseUid);
    }

    @PostMapping("/group-prefereces")
    public void preferUser(@RequestBody List<PrefData> prefData, HttpSession session) {
        UserModel userModel = (UserModel) session.getAttribute("userModel");
        courseService.addUserPreferences(prefData, userModel);
    }

    @PostMapping("/generate-groups")
    public void generateGroups(@RequestParam Integer numOfGroups, List<String> uid) {

    }

}
