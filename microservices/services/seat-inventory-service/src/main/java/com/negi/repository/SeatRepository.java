package com.negi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.negi.model.Seat;

public interface SeatRepository extends JpaRepository<Seat,Long>{
    
}
