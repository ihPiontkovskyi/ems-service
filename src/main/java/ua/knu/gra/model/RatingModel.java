package ua.knu.gra.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "ratings")
public class RatingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private TaskModel task;

    @Column
    private Integer value;

    @Column
    private String answer;

    @ManyToOne
    private CourseModel course;

    @OneToOne
    private UserModel user;

}
