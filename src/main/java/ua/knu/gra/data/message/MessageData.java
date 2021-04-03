package ua.knu.gra.data.message;

import lombok.Getter;
import lombok.Setter;
import ua.knu.gra.data.UserData;

import java.sql.Date;

@Setter
@Getter
public class MessageData {

    private Integer sequenceNumber;

    private Date createdAt;

    private String content;

    private UserData owner;

}
