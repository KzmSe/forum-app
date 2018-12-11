package com.forum.admin.model;

import lombok.Data;
import org.hibernate.boot.model.source.spi.FetchCharacteristics;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private int id;

    @Column(name = "role_type")
    private String roleType;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_action",
            joinColumns = @JoinColumn(name = "id_role"),
            inverseJoinColumns = @JoinColumn(name = "id_action"))
    private List<Action> actions;

}
