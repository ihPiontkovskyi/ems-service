package ua.knu.gra.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ua.knu.gra.data.group.GroupData;
import ua.knu.gra.data.group.GroupMainPageData;
import ua.knu.gra.service.GroupService;

import javax.annotation.Resource;
import java.util.List;

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

}
