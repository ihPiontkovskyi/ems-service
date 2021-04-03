package ua.knu.gra.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "user_groups")
public class GroupModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String uid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "group")
    private Set<PostModel> posts;

    @OneToMany(mappedBy = "group")
    private Set<MessageModel> messages;

    @OneToOne
    private UserModel lecturer;

    @ManyToMany
    private Set<UserModel> users;
}
