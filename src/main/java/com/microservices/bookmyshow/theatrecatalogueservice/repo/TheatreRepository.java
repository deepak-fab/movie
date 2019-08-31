package com.microservices.bookmyshow.theatrecatalogueservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.microservices.bookmyshow.theatrecatalogueservice.entity.ShowDTL;
import com.microservices.bookmyshow.theatrecatalogueservice.entity.Theatre;


public interface TheatreRepository extends JpaRepository<Theatre, Integer>{
	
	public List<Theatre> findByCityIdAndMovieId(int cityId, int movieId);
	
}
