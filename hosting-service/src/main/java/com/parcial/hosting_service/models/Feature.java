package com.parcial.hosting_service.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "features")
@Builder
@AllArgsConstructor
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Boolean hasBuffet;

    @Column(nullable = false)
    private Boolean hasFridge;

    @Column(nullable = false)
    private Boolean hasSwimmingPool;

    @Column(nullable = false)
    private Boolean hasWifi;
}
