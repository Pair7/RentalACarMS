package com.appdeveloperblog.rentalapp.api.users.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "user_id")
@Table(name = "corporate_customers")
public class CorporateCustomer extends UserEntity {

    @Column(name="company_name",nullable = false,length = 100)
    private String companyName;
    @Column(name="tax_number", nullable = false,length = 50)
    private String taxNumber;
}
