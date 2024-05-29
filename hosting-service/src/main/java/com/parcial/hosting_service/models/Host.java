package com.parcial.hosting_service.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "hosts")
@Builder
@AllArgsConstructor
public class Host {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer rating;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer maximumCapacity;

    @Column(nullable = false)
    private String latitude;

    @Column(nullable = false)
    private String longitude;

    @Column(nullable = false)
    private String originName;

    @OneToOne(optional = true)
    @JoinColumn(name = "pictures", referencedColumnName = "id")
    private Picture picture;

    @OneToOne(optional = true)
    @JoinColumn(name = "features", referencedColumnName = "id")
    private Feature feature;

}
