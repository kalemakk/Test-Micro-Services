package com.vr.touristService.Service;

import com.vr.touristService.Entity.TouristEntity;
import com.vr.touristService.Exception.TouristAlreadyExistsException;
import com.vr.touristService.Exception.TouristNotFoundException;
import com.vr.touristService.Repository.TouristRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class TouristService {
    @Autowired
    private TouristRepository touristRepository;

    public List<TouristEntity> findAllTourists(){
        log.info("Inside method of TouristService");
        return touristRepository.findAll();
    }

    public TouristEntity findTourist(Long touristId){
        log.info("Inside method of TouristService");
        return touristRepository.findById(touristId).orElseThrow(()-> new TouristNotFoundException("Tourist "+touristId+" doesn't exist"));
    }

    public TouristEntity createTourist(TouristEntity tourist){
        log.info("Inside method of TouristService");
        if(Objects.nonNull(touristRepository.findByTelephone(tourist.getTelephone()))){
            throw new TouristAlreadyExistsException("Tourist with telephone "+tourist.getTelephone()+" is already registered");
        }
        tourist.setCreatedBy(tourist.getFirstName());
        tourist.setWhenCreated(LocalDateTime.now());
        tourist.setWhenUpdated(LocalDateTime.now());
        tourist.setStatus(true);
        return touristRepository.save(tourist);
    }

    public String deleteTourist(Long touristId){
        log.info("Inside method of TouristService");
        String result = null;
        if(Objects.nonNull(findTourist(touristId))){
            touristRepository.deleteById(touristId);
           result = "Tourist "+touristId+" successfully deleted";
        }
        return result;
    }

    public TouristEntity updateTourist(TouristEntity tourist, Long touristId){
        log.info("Inside method of TouristService");
        TouristEntity oldTourist = findTourist(touristId);
        TouristEntity updatedTourist = null;
        if(Objects.nonNull(oldTourist)){
            tourist.setTouristId(touristId);
            tourist.setCreatedBy(oldTourist.getCreatedBy());
            tourist.setWhenCreated(oldTourist.getWhenCreated());
            tourist.setWhenUpdated(LocalDateTime.now());
            tourist.setStatus(oldTourist.isStatus());
            updatedTourist = touristRepository.save(tourist);
        }
        return updatedTourist;
    }
}
