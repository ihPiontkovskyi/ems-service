package ua.knu.gra.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "tasks")
public class TaskModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String summary;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Integer maxRating;

    @ManyToOne
    private CourseModel course;

    @OneToMany(mappedBy = "task")
    private Set<RatingModel> ratings;
}
