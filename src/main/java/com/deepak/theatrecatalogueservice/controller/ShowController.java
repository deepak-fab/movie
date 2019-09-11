package com.deepak.theatrecatalogueservice.controller;

import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.deepak.theatrecatalogueservice.constants.ApplicationConstants;
import com.deepak.theatrecatalogueservice.dto.BookingDetailsDTO;
import com.deepak.theatrecatalogueservice.dto.SeatInfoDTO;
import com.deepak.theatrecatalogueservice.dto.TheatreDTO;
import com.deepak.theatrecatalogueservice.entity.Seat;
import com.deepak.theatrecatalogueservice.entity.ShowDTL;
import com.deepak.theatrecatalogueservice.entity.Theatre;
import com.deepak.theatrecatalogueservice.repo.SeatRepository;
import com.deepak.theatrecatalogueservice.repo.ShowRepository;
import com.deepak.theatrecatalogueservice.repo.TheatreRepository;
import com.deepak.theatrecatalogueservice.service.ShowService;


@RestController
public class ShowController {


	@Autowired
	ShowService showService;


	@GetMapping("/theatres")
	public @ResponseBody List<Theatre> getTheatres(@RequestParam("cityId") int cityId, @RequestParam("movieId") int movieId) throws ParseException{
		
		if(cityId==0 || movieId == 0) {
			throw new ParseException(" Missing query parameters",0);
		}
		return	showService.getTheatres(cityId,movieId);		
	}
	
	@GetMapping("/theatres/{theatreId}/shows")
	public  @ResponseBody List<ShowDTL> getShows(@PathVariable("theatreId") int theatreId) throws ParseException{
		
		if(theatreId==0) {
			throw new ParseException(" Missing query parameters",0);
		}
		
		return	showService.getShows(theatreId);
		
	}

    @PostMapping("/bookseats")
    public BookingDetailsDTO bookSeats(@RequestBody SeatInfoDTO seatInfoDTO) 
    {
    	
    	return showService.bookSeats(seatInfoDTO);
    }
    
    @PostMapping("/addTheatre")
    public Theatre addTheatre(@RequestBody Theatre theatre) 
    {
    	   
    	return showService.addTheatre(theatre);
    	
    }    
    
    @PostMapping("/addShow")
    public ShowDTL addShowDTL(@RequestBody ShowDTL showDTL) 
    {
    	 return showService.addShowDTL(showDTL);	
    }        
    
    @PostMapping("/addSeat")
    public Seat addSeat(@RequestBody Seat seat) 
    {
    	return showService.addSeat(seat);	
    }          
    
    @PutMapping("/updateTheatre")
    public Theatre updateTheatre(@RequestBody Theatre theatre) 
    {  
    	return showService.updateTheatre(theatre);
    	
    }        
 
    @DeleteMapping("/deleteTheatre")
    public String deleteTheatre(@RequestParam int theatreId) 
    {        
    	    return showService.deleteTheatre(theatreId);
    }      
    
}
