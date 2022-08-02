package com.fms.trainings.security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fms.trainings.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class Uuser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    /*@NotNull(message = "Can't be null!")
    @Size(min = 4, max = 25)*/
    @Column(name = "USER_NAME")
    private String userName;

   /* @NotNull
    @Size(min = 4)*/
    //@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "The password must contain at least one lowercase character, one uppercase character, one digit, one special character, and a length between 8 to 20. The below regex uses positive lookahead for the conditional checking.")
    private String password;

    private Boolean active;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Rrole> roles = new ArrayList<>();

   /* @OneToMany(mappedBy = "uuser")
    private List<Customer> customers;*/

    @Override
    public String toString() {
        return "Uuser [userId=" + userId + ", userName=" + userName + ", password=" + password + ", active=" + active
                + ", roles=" + roles + "]";
    }

    public Uuser(long userId, @NotNull String userName,

                 @NotNull String password, Boolean active) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.active = active;
    }

}
