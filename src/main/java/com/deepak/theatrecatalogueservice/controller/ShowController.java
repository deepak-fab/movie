package com.deepak.theatrecatalogueservice.controller;

import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.deepak.constants.ApplicationConstants;
import com.deepak.theatrecatalogueservice.dto.BookingDetailsDTO;
import com.deepak.theatrecatalogueservice.dto.SeatInfoDTO;
import com.deepak.theatrecatalogueservice.dto.TheatreDTO;
import com.deepak.theatrecatalogueservice.entity.Seat;
import com.deepak.theatrecatalogueservice.entity.ShowDTL;
import com.deepak.theatrecatalogueservice.entity.Theatre;
import com.deepak.theatrecatalogueservice.repo.SeatRepository;
import com.deepak.theatrecatalogueservice.repo.ShowRepository;
import com.deepak.theatrecatalogueservice.repo.TheatreRepository;


@RestController
public class ShowController {

	@Autowired TheatreRepository theatreRepo;
	@Autowired ShowRepository showRepo;

    @Autowired
    private SeatRepository seatRepo;

	@GetMapping("/theatres")
	public @ResponseBody List<Theatre> getTheatres(@RequestParam("cityId") int cityId, @RequestParam("movieId") int movieId) throws ParseException{
		
		if(cityId==0 || movieId == 0) {
			throw new ParseException(" Missing query parameters",0);
		}
		
		List<Theatre> result = theatreRepo.findByCityIdAndMovieId(cityId, movieId);
		
		return result;
		
	}
	
	@GetMapping("/theatres/{theatreId}/shows")
	public  @ResponseBody List<ShowDTL> getShows(@PathVariable("theatreId") int theatreId) throws ParseException{
		
		if(theatreId==0) {
			throw new ParseException(" Missing query parameters",0);
		}
		
		List<ShowDTL> shows = showRepo.findByTheatreId(theatreId);
		
		return shows;
		
	}

    @PostMapping("/bookseats")
    public BookingDetailsDTO bookSeats(@RequestBody SeatInfoDTO seatInfoDTO) 
    {
        List<Seat> selectedSeats = seatRepo.findByShowIdAndSeatNameIn(seatInfoDTO.getShowId(), seatInfoDTO.getSelectedSeats());
        if (selectedSeats.stream().anyMatch(seat -> seat.getStatus() != 0)) {
            return null;
        }
        double totalCost = selectedSeats.stream().mapToDouble(seat -> seat.getPrice()).sum();
        selectedSeats.stream().forEach(seat -> seat.setStatus(1));
        selectedSeats.stream().forEach(seat -> seatRepo.save(seat));
        BookingDetailsDTO bookingDetails = new BookingDetailsDTO(seatInfoDTO.getShowId(), "test user", totalCost, java.time.LocalDateTime.now(), selectedSeats.size());
        return bookingDetails;
    }
    
    @PostMapping("/addTheatre")
    public Theatre addTheatre(@RequestBody Theatre theatre) 
    {
    	return theatreRepo.save(theatre);	
    }    
    
    @PostMapping("/addShow")
    public ShowDTL addShowDTL(@RequestBody ShowDTL showDTL) 
    {
    	return showRepo.save(showDTL);	
    }        
    
    @PostMapping("/addSeat")
    public Seat addShowDTL(@RequestBody Seat seat) 
    {
    	return seatRepo.save(seat);	
    }          
    
    @PostMapping("/updateTheatre")
    public Theatre updateTheatre(@RequestBody Theatre theatre) 
    {  
    	return theatreRepo.save(theatre);	
    }        
 
    @PostMapping("/deleteTheatre")
    public String deleteTheatre(@RequestParam int theatreId) 
    {        
    	    Theatre theatre = theatreRepo.findById(theatreId).orElse(null);
    	    if(theatre==null) {
    	    	return ApplicationConstants.NO_THEATRE;
    	    }
    	    
    	    List<ShowDTL> showList =  showRepo.findByTheatreId(theatreId);
    	    
    	    if(showList.isEmpty()) {
    	    	theatreRepo.deleteById(theatreId);
    	    	return ApplicationConstants.SUCCESS;
    	    }
    	    
    	    Set<Integer> showSet = new HashSet<>();
    	    for (ShowDTL showDTL : showList) {
				showSet.add(showDTL.getShowId());
			}
    	    
    	  List<Seat> seatList =   seatRepo.findByShowIdIn(showSet);
    	      
    	  seatList = seatList.stream().filter(a ->  a.getStatus()==1).collect(Collectors.toList());
    	  if(seatList.isEmpty()) {
      	    theatreRepo.deleteById(theatreId);	
      		return ApplicationConstants.SUCCESS;
    	  }
            return  ApplicationConstants.TICKET_BOOKED;
    }      
    
}
