package ua.knu.gra.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "posts")
public class PostModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String summary;

    @Column(nullable = false)
    private String content;

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Integer sequenceNumber;

    @ManyToOne
    private GroupModel group;

    @ManyToOne
    private CourseModel course;
}
