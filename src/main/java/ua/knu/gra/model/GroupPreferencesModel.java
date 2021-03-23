package ua.knu.gra.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "group_preferences")
public class GroupPreferencesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String userUid;
    @Column
    private String prefUserUid;
    @Column
    private Integer priority;
}
