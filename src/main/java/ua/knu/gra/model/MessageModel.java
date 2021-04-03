package ua.knu.gra.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "messages")
public class MessageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Integer sequenceNumber;

    @Column
    @CreationTimestamp
    private Date createdAt;

    @Column
    private String content;

    @OneToOne
    private UserModel owner;

    @ManyToOne
    private GroupModel group;
}
