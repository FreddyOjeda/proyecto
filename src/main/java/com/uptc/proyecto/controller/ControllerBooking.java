package com.uptc.proyecto.controller;

import com.uptc.proyecto.entity.Booking;
import com.uptc.proyecto.response.ResponseHandler;
import com.uptc.proyecto.service.BookingService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class ControllerBooking {
    private BookingService  bookingService;

    public ControllerBooking(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<Object> findAll(){
        try {
            List<Booking> result = bookingService.findAll();
            return new ResponseHandler().generateResponse("Search global completed", HttpStatus.OK, result);
        }catch (Exception e){

            return new ResponseHandler().generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id){
        try {
            Booking result = bookingService.findById(id);
            return new ResponseHandler().generateResponse("Search by id Completed!", HttpStatus.OK,result);
        }catch (Exception e){

            return new ResponseHandler().generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Booking booking){
        try{
            Booking result = bookingService.save(booking);
            return new ResponseHandler().generateResponse("Save Completed!", HttpStatus.OK,result);
        }catch (Exception e){
            return new ResponseHandler().generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Integer id,@RequestBody Booking booking){
        try{
            Booking result = bookingService.update(id,booking);
            return new ResponseHandler().generateResponse("Update Completed!", HttpStatus.OK,result);
        }catch(Exception e ){

            return new ResponseHandler().generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        try{
            Boolean result = bookingService.delete(id);
            return new ResponseHandler().generateResponse("Delete Completed!", HttpStatus.OK,result);
        }catch(Exception e ){

            return new ResponseHandler().generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,null);
        }
    }
}
