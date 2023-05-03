package com.uptc.proyecto.service;


import com.uptc.proyecto.entity.Booking;
import com.uptc.proyecto.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    BookingRepository repository;

    public Booking save(Booking booking){
        List<Booking> aux= repository.findByCar(booking.getCar());
        for (Booking b: aux) {
            if ((booking.getBooking().after(b.getBooking()) || booking.getDelivery().after(b.getBooking()))
            && (booking.getBooking().before(b.getDelivery()) || booking.getDelivery().before(b.getDelivery()))){
                return null;
            }
        }
        return repository.save(booking);
    }

    public Booking findById(int id){
        Optional<Booking> optional = repository.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }else {
            return null;
        }
    }

    public List<Booking> findAll(){
        return repository.findAll();
    }

    public Booking update(int id,Booking booking){
        Optional<Booking> optional = repository.findById(id);
        List<Booking> aux= repository.findByCar(booking.getCar());
        for (Booking b: aux) {
            if (b.getId() != booking.getId()){
                if ((booking.getBooking().after(b.getBooking()) || booking.getDelivery().after(b.getBooking()))
                        && (booking.getBooking().before(b.getDelivery()) || booking.getDelivery().before(b.getDelivery()))){
                    return null;
                }
            }
        }
        if (optional.isPresent()){
            Booking newBooking = optional.get();
            newBooking.setPerson(booking.getPerson());
            newBooking.setCar(booking.getCar());
            newBooking.setBooking(booking.getBooking());
            newBooking.setDelivery(booking.getDelivery());
            newBooking.setObservations(booking.getObservations());
            repository.save(newBooking);
            return newBooking;
        }else {
            return null;
        }
    }

    public boolean delete(int id){
        Optional<Booking> optional = repository.findById(id);
        if (optional.isPresent()){
            repository.deleteById(id);
            return true;
        }else {
            return false;
        }

    }
}
