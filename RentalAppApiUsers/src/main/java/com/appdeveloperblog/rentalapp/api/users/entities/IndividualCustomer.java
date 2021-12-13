package com.appdeveloperblog.rentalapp.api.users.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "user_id")
@Table(name = "individual_customers")
public class IndividualCustomer extends UserEntity {

    @Column(name = "first_name",nullable = false,length = 50)
    private String firstName;

    @Column(name = "last_name",nullable = false,length = 50)
    private String lastName;

    @Column(name = "birthday",nullable = false)
    private LocalDate birthday;



}
