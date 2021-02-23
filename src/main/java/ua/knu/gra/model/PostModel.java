package ua.knu.gra.model;

import javax.persistence.*;

@Entity
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
}
