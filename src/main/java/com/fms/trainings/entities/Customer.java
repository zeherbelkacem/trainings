package com.fms.trainings.entities;

import com.fms.trainings.security.entities.Uuser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
@Data @AllArgsConstructor @NoArgsConstructor
public class Customer {

    /**
     *.
     */
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
  /*  @NotNull(message = "Can't be null!")
    @Size(min = 4, max = 25)*/
    private String firstName;

    @Column
    /*@NotNull(message = "Can't be null!")
    @Size(min = 4, max = 25)*/
    private String lastName;

    @Column
//    @Email(regexp = ".+[@].+[\\.].+", message = "Please enter à valid mail")
    private String email;

    @Column(unique = true)
//    @Pattern(regexp = "^[0-9]{10}$", message = "Your number phone format is incorrect") //0000000000
    private String phone;

    @Column
    private Address address;

    @ManyToOne
    private Uuser uuser;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
}