package com.fms.trainings.security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "roles")
public class Rrole {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long roleId;

    @Column(name = "ROLE")
//	@Enumerated(EnumType.STRING)
    private String role;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private List<Uuser> users = new ArrayList<Uuser>();

    public Rrole(long id, String role) {
        this.roleId = id;
        this.role = role;
    }


}
