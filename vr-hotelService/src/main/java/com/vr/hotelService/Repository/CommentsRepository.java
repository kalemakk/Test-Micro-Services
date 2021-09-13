package com.vr.hotelService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vr.hotelService.Entity.CommentsEntity;

public interface CommentsRepository extends JpaRepository<CommentsEntity, Long> {

}
