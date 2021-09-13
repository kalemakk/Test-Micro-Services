package com.vr.hotelService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vr.hotelService.Entity.HotelEntity;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity,Long> {
	HotelEntity findByTelephoneNumber(String telephoneNumber);
	HotelEntity findByEmail(String email);
}
