package ua.knu.gra.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "courses")
public class CourseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "course")
    private Set<DocumentModel> documents;

    @OneToMany(mappedBy = "course")
    private Set<TaskModel> tasks;

    @OneToMany(mappedBy = "course")
    private Set<RatingModel> ratings;

    @ManyToMany
    private Set<UserModel> users;

}
