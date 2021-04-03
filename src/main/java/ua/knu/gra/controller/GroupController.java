package ua.knu.gra.controller;

import org.springframework.web.bind.annotation.*;
import ua.knu.gra.data.message.MessageAddData;
import ua.knu.gra.data.message.MessageData;
import ua.knu.gra.data.group.GroupData;
import ua.knu.gra.data.group.GroupMainPageData;
import ua.knu.gra.model.UserModel;
import ua.knu.gra.service.GroupService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController("/webapi/groups")
public class GroupController {

    @Resource
    private GroupService groupService;

    @GetMapping(value = "/all-groups", produces = APPLICATION_JSON_VALUE)
    public List<GroupMainPageData> getAllGroups() {
        return groupService.getAll();
    }

    @GetMapping(value = "/{groupUid}/details", produces = APPLICATION_JSON_VALUE)
    public GroupData getGroupDetails(@PathVariable String groupUid) {
        return groupService.getByUid(groupUid);
    }

    @DeleteMapping("/{groupUid}/delete")
    public void delete(@PathVariable String groupUid) {
        groupService.deleteByUid(groupUid);
    }

    @PostMapping("/{groupUid}/send-message")
    public void sendMessage(@PathVariable String groupUid, @RequestBody MessageAddData data, HttpSession session) {
        UserModel userModel = (UserModel) session.getAttribute("userModel");
        groupService.sendMessage(data, groupUid, userModel);
    }

    @GetMapping(value = "/{groupUid}/refresh-message", produces = APPLICATION_JSON_VALUE)
    public Set<MessageData> sendMessage(@PathVariable String groupUid) {
        return groupService.getRefreshedData(groupUid);
    }
}
