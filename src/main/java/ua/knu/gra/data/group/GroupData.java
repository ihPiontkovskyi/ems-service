package ua.knu.gra.data.group;

import lombok.Getter;
import lombok.Setter;
import ua.knu.gra.data.message.MessageData;
import ua.knu.gra.data.post.PostData;
import ua.knu.gra.data.UserData;

import java.util.Set;

@Getter
@Setter
public class GroupData {
    private String name;
    private String description;
    private UserData lecturer;
    private Set<PostData> posts;
    private Set<MessageData> messages;
}
