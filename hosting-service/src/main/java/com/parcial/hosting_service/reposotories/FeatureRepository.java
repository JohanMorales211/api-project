package com.parcial.hosting_service.reposotories;

import com.parcial.hosting_service.models.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Integer> {

}
