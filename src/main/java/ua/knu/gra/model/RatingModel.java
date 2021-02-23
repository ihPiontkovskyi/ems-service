package ua.knu.gra.model;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
public class RatingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private TaskModel task;

    @Column(nullable = false)
    private Integer value;

    @ManyToOne
    private CourseModel course;

}
