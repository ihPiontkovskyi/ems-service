package ua.knu.gra.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "courses")
public class CourseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String uid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "course")
    private Set<TaskModel> tasks;

    @OneToMany(mappedBy = "course")
    private Set<PostModel> posts;

    @OneToOne
    private UserModel lecturer;

    @ManyToMany
    private Set<UserModel> users;

}
