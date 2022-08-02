package com.fms.trainings.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CATEGORIES")
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Category implements Serializable {
    //TODO
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", unique = true)
    private String name;


    @OneToMany(mappedBy = "category")
    @JsonIgnore
//    @JsonManagedReference
    private List<Training> trainings = new ArrayList<>();
}