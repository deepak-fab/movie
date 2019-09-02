package com.deepak.theatrecatalogueservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deepak.theatrecatalogueservice.entity.Seat;


public interface SeatRepository extends JpaRepository<Seat, Integer>
{
    public List<Seat> findByShowIdAndSeatNameIn(int showId, List<String> seatNumbers);

}
