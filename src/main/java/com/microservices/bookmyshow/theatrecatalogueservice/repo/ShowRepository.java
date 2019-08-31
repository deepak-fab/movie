package com.microservices.bookmyshow.theatrecatalogueservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.microservices.bookmyshow.theatrecatalogueservice.entity.ShowDTL;

public interface ShowRepository extends JpaRepository<ShowDTL, Long>{
	
	@Query(" select showId, theatreId, showDateTime, totalSeats, availableSeats from ShowDTL s where s.theatreId = :theatreId")
	public List<ShowDTL> getShowList(@Param("theatreId") int theatreId);

	public List<ShowDTL> findByTheatreId(int theatreId);

}
