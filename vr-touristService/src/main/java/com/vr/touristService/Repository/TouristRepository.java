package com.vr.touristService.Repository;

import com.vr.touristService.Entity.TouristEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TouristRepository extends JpaRepository<TouristEntity, Long> {
    TouristEntity findByTelephone(String telephone);
}
