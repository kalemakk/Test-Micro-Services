package com.vr.hotelService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vr.hotelService.Entity.AttachmentsEntity;

@Repository
public interface AttachmentsRepository extends JpaRepository<AttachmentsEntity, Long> {

}
