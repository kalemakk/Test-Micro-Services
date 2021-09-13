package com.vr.touristService.Controller;

import com.vr.touristService.Entity.TouristEntity;
import com.vr.touristService.Service.TouristService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("api/v1/tourists")
public class TouristController {
    @Autowired
    private TouristService touristService;
    @GetMapping
    public ResponseEntity<List<TouristEntity>> findAllTourists(){
        log.info("inside findAllTourists method of TouristController");
        return ResponseEntity.status(HttpStatus.OK).body(touristService.findAllTourists());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TouristEntity> findTourist(@PathVariable("id") Long touristId){
        log.info("inside findTourist method of TouristController");
        return ResponseEntity.status(HttpStatus.OK).body(touristService.findTourist(touristId));
    }

    @PostMapping
    public ResponseEntity<TouristEntity> createTourist(@RequestBody TouristEntity tourist){
        log.info("inside createTourist method of TouristController");
        return ResponseEntity.status(HttpStatus.CREATED).body(touristService.createTourist(tourist));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTourist(@PathVariable("id") Long touristId){
        log.info("inside deleteTourist method of TouristController");
        return ResponseEntity.status(HttpStatus.OK).body(touristService.deleteTourist(touristId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TouristEntity> updateTourist(@PathVariable("id") Long touristId, @RequestBody TouristEntity tourist){
        log.info("inside updateTourist method of TouristController");
        return ResponseEntity.status(HttpStatus.OK).body(touristService.updateTourist(tourist,touristId));
    }

}
