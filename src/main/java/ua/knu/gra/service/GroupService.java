package ua.knu.gra.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.knu.gra.data.group.GroupData;
import ua.knu.gra.data.group.GroupMainPageData;
import ua.knu.gra.model.GroupModel;
import ua.knu.gra.model.UserModel;
import ua.knu.gra.repository.GroupRepository;
import ua.knu.gra.service.common.MapperUtil;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GroupService {
    private final GroupRepository groupRepository;

    public List<GroupMainPageData> getAll() {
        return groupRepository.findAll()
                .stream()
                .map(MapperUtil::mapToMainPageGroupData)
                .collect(Collectors.toList());
    }

    public GroupData getByUid(String uid) {
        GroupModel Group = groupRepository.findByUid(uid).orElseThrow(() -> new RuntimeException("Invalid uid"));

        return MapperUtil.mapToGroupData(Group);
    }

    public void deleteByUid(String uid) {
        GroupModel GroupModel = groupRepository.findByUid(uid).orElseThrow(() -> new RuntimeException("Invalid uid"));
        groupRepository.delete(GroupModel);
    }

}
