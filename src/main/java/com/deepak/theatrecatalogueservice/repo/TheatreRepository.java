package com.deepak.theatrecatalogueservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deepak.theatrecatalogueservice.entity.Theatre;


public interface TheatreRepository extends JpaRepository<Theatre, Integer>{
	
	public List<Theatre> findByCityIdAndMovieId(int cityId, int movieId);
	
}
