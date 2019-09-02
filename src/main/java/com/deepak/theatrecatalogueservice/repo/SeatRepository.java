package com.deepak.theatrecatalogueservice.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.deepak.theatrecatalogueservice.entity.Seat;


public interface SeatRepository extends JpaRepository<Seat, Integer>
{
    public List<Seat> findByShowIdAndSeatNameIn(int showId, List<String> seatNumbers);
	
/*    @Query("delete from ItemCategory where prodCode in (:prodCodes)")
	void deleteByCodes(@Param("prodCodes") Set<String> prodCodes);*/
    
    public List<Seat> findByShowIdIn(Set<Integer> showSet);
	
    
}
