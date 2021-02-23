package ua.knu.gra.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user_groups")
public class GroupModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToMany
    private Set<UserModel> users;

    @OneToMany(mappedBy = "group")
    private Set<PostModel> posts;
}
