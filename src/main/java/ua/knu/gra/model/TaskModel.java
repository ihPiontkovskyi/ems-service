package ua.knu.gra.model;


import javax.persistence.*;

@Entity
@Table(name = "tasks")
public class TaskModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String summary;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    private CourseModel course;

}
