package ua.knu.gra.controller;

import org.springframework.web.bind.annotation.*;
import ua.knu.gra.data.post.PostAddData;
import ua.knu.gra.service.PostService;

import javax.annotation.Resource;

@RestController("/webapi/")
public class PostController {

    @Resource
    private PostService postService;

    @PostMapping("/groups/{groupUid}/add-post")
    public void addPostToGroup(@PathVariable String groupUid, @RequestBody PostAddData data) {
        postService.addPostToGroup(groupUid, data);
    }

    @PostMapping("/courses/{courseUid}/add-post")
    public void addPostToCourse(@PathVariable String courseUid, @RequestBody PostAddData data) {
        postService.addPostToCourse(courseUid, data);
    }

    @DeleteMapping("/courses/{courseUid}/delete-post")
    public void deleteCoursePost(@PathVariable String courseUid, @RequestParam Integer seqNum) {
        postService.deleteForCourse(courseUid, seqNum);
    }

    @DeleteMapping("/groups/{groupUid}/delete-post")
    public void deleteGroupPost(@PathVariable String groupUid, @RequestParam Integer seqNum) {
        postService.deleteForGroup(groupUid, seqNum);
    }
}
