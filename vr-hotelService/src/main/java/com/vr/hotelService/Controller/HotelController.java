package com.vr.hotelService.Controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vr.hotelService.Service.HotelService;
import com.vr.hotelService.Entity.HotelEntity;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/hotels")
public class HotelController {
	@Autowired
	private HotelService hotelService;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping
	public List<HotelEntity> findAllHotels(){
		logger.info("inside findAllHotels of HotelController");
		return hotelService.findAllHotels();
	}
	
	@GetMapping("/{id}")
	public HotelEntity findHotel(@PathVariable("id") Long hotelId) {
		logger.info("inside findHotel of HotelController");
		return hotelService.findHotel(hotelId);		
	}
	
	@PostMapping
	public HotelEntity createHotel(@RequestBody HotelEntity hotel) {
		logger.info("inside create of HotelController");
		return hotelService.createHotel(hotel);
	}
	
	@DeleteMapping("/{id}")
	public String deleteHotel(@PathVariable("id") Long hotelId ) {
		logger.info("inside deleteHotel of HotelController");
		return hotelService.deleteHotel(hotelId);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<HotelEntity> updateHotel(@PathVariable("id") Long hotelId, @RequestBody HotelEntity hotel) {
		logger.info("inside updateHotel of HotelController");
		return ResponseEntity.ok(this.hotelService.updateHotel(hotel, hotelId));
	}
	
	@PostMapping("/approve/{id}")
	public HotelEntity approveHotel(@PathVariable("id") Long hotelId) {
		return hotelService.approveHotel(hotelId);
	}
}
