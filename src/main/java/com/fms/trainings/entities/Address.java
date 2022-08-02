package com.fms.trainings.entities;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Stagiaires11P
 *
 */

@Data @NoArgsConstructor @AllArgsConstructor
@Embeddable
public class Address {


    private String address;

    private String address2;

    private String codeZip;

    private String city;

    private String country;

}

