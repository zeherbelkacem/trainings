package com.fms.trainings.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor @NoArgsConstructor @ToString
@Table(name = "TRAINING")
@Entity
public class Training implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name= "PRICE")
    private Double price;

    @Column(name = "QUANTITY")
    private int quantity = 1;

    @Column(name = "image")
    private String image;

    @Column(name = "AVAILABLE")
    private Boolean available;

    @Column(name = "SELECTED")
    private Boolean selected;

    @Column(name = "START_DATE")
    private LocalDate startDate;

    @Column(name = "END_DATE")
    private LocalDate endDate;

    @Column(name = "DURATION")
    private String duration;

   @ManyToOne(fetch = FetchType.EAGER)
//   @JsonBackReference
   private Category category;


}
