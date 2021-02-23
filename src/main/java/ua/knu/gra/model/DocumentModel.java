package ua.knu.gra.model;

import javax.persistence.*;

@Entity
@Table(name = "documents")
public class DocumentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    private CourseModel course;

    //TODO add file storing
}
