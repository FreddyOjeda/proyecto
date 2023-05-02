package com.uptc.proyecto.repository;

import com.uptc.proyecto.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    public List<Booking> findByCar(String car);
}
