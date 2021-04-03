package ua.knu.gra.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.knu.gra.data.group.GroupData;
import ua.knu.gra.data.group.GroupMainPageData;
import ua.knu.gra.data.message.MessageAddData;
import ua.knu.gra.data.message.MessageData;
import ua.knu.gra.model.GroupModel;
import ua.knu.gra.model.MessageModel;
import ua.knu.gra.model.UserModel;
import ua.knu.gra.repository.GroupRepository;
import ua.knu.gra.repository.MessageRepository;
import ua.knu.gra.repository.UserRepository;
import ua.knu.gra.service.common.MapperUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GroupService {
    private final GroupRepository groupRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

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

    public void sendMessage(MessageAddData data, String groupUid, String userUid) {
        UserModel current = userRepository.findUserModelByUid(userUid).orElseThrow(() -> new RuntimeException("Invalid uid"));
        MessageModel message = new MessageModel();
        message.setContent(data.getContent());
        message.setOwner(current);
        message.setGroup(groupRepository.findByUid(groupUid).get());
        messageRepository.save(message);
    }

    public Set<MessageData> getRefreshedData(String groupUid) {
        return MapperUtil.mapAllToMessageData(new HashSet<>(messageRepository.findAllByGroupUId(groupUid)));
    }
}
