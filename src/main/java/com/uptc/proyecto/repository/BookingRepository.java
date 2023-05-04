package com.uptc.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.uptc.proyecto.entity.Booking;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    public List<Booking> findByCar(String car);
}
