package com.parcial.reservation_service.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
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

    @OneToOne(optional = true)
    @JoinColumn(name = "pictures", referencedColumnName = "id")
    private PictureDTO picture;

    @OneToOne(optional = true)
    @JoinColumn(name = "features", referencedColumnName = "id")
    private FeatureDTO feature;



}
