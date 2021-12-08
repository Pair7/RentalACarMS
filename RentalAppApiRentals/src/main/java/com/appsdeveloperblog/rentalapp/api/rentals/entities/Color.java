package com.appsdeveloperblog.rentalapp.api.rentals.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "colors")

public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "color_id")
    private int colorId;

    @Column(name = "colorName",nullable = false,length = 50)
    private String colorName;

    @OneToMany(mappedBy = "color")
    private List<Car> cars;
}
