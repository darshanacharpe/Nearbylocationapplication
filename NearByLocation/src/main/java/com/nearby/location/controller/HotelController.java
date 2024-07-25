package com.nearby.location.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nearby.location.entity.Hotel;
import com.nearby.location.services.HotelServiceImpl;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {
	private static final Logger logger = LoggerFactory.getLogger(HotelController.class);

	@Autowired
	private HotelServiceImpl hotelService;
//
//	@GetMapping("/nearby")
//	public ResponseEntity<List<Hotel>> findNearbyHotels(@RequestParam double latitude, @RequestParam double longitude) {
//		logger.debug("Received request to find nearby hotels with latitude: {} and longitude: {}", latitude, longitude);
//
//		try {
//			List<Hotel> hotels = hotelService.findNearbyHotels(latitude, longitude);
//			logger.debug("Returning {} hotels", hotels.size());
//			return ResponseEntity.ok(hotels);
//		} catch (Exception e) {
//			logger.error("Error finding nearby hotels", e);
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//		}
//	}
	

    @GetMapping("/nearby-hotels")
   
    public ResponseEntity<List<Hotel>> getNearbyHotels(@RequestParam double latitude, @RequestParam double longitude) {
    	 try {
        List<Hotel> hotels = hotelService.findNearbyHotels(latitude, longitude);
        return ResponseEntity.ok(hotels);
    }
    	 catch (Exception e) {
 			logger.error("Error finding nearby hotels", e);
 			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
 		}
    }
	
   
}
