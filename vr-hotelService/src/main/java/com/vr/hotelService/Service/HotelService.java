package com.vr.hotelService.Service;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vr.hotelService.Repository.HotelRepository;

import com.vr.hotelService.Entity.HotelEntity;
import com.vr.hotelService.Exception.HotelAlreadyExistsException;
import com.vr.hotelService.Exception.HotelNotFoundException;


@Service
public class HotelService {
	@Autowired
	private HotelRepository hotelRepo;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public List<HotelEntity> findAllHotels(){
		logger.info("Inside findAllHotels method of HotelService");
		return hotelRepo.findAll();
	}
	
	public HotelEntity findHotel(Long hotelId) {
		logger.info("Inside findHotel method of HotelService");
		return hotelRepo.findById(hotelId).orElseThrow(()-> 
		new HotelNotFoundException("Hotel with Id "+hotelId +" doesn't exist"));
	}
	
	public HotelEntity createHotel(HotelEntity hotel) {
		logger.info("Inside createHotel method of HotelService");
		if(hotelExists(hotel.getEmail(),hotel.getTelephoneNumber())) {
			throw new HotelAlreadyExistsException(" Hotel with Telephone "+hotel.getTelephoneNumber()+ " or Email " +hotel.getEmail()+ " already exists");
		}
		hotel.setStatus(false);
		return hotelRepo.save(hotel);
	}
	
	public String deleteHotel(Long hotelId) {
		logger.info("Inside delete`Hotel method of HotelService");
		if(Objects.nonNull(findHotel(hotelId))) {
			hotelRepo.deleteById(hotelId);
		}
		return "Hotel with id "+hotelId+" has been deleted";
	}
	
	public HotelEntity findHotelByTelephone(String telephone) {
		logger.info("Inside findHotelByTelephone method of HotelService");
		return hotelRepo.findByTelephoneNumber(telephone);		
	}
	
	public HotelEntity findHotelByEmail(String email) {
		logger.info("Inside findHotelByEmail method of HotelService");
		return hotelRepo.findByEmail(email);
	}
	
	public boolean hotelExists(String email, String telephone) {
		logger.info("Inside hotelExist method of HotelService");
		HotelEntity hotelByTelephone = findHotelByTelephone(telephone);
		HotelEntity hotelByEmail = findHotelByEmail(email);
		if((Objects.isNull(hotelByEmail) || Objects.isNull(hotelByTelephone))){
			return false;
		}
		return true;
	}
	
	public HotelEntity updateHotel(HotelEntity hotel, Long hotelId) {
		HotelEntity oldHotel = findHotel(hotelId);
		if(Objects.nonNull(oldHotel)) {
			hotel.setHotelId(hotelId);
		}
		return hotelRepo.save(hotel);
	}
	
	public HotelEntity approveHotel(Long hotelId) {
		HotelEntity hotel = findHotel(hotelId);
		hotel.setStatus(true);
		hotel.setHotelId(hotelId);
		return hotelRepo.save(hotel);
	}	
}
