package com.deepak.theatrecatalogueservice.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.deepak.theatrecatalogueservice.constants.ApplicationConstants;
import com.deepak.theatrecatalogueservice.dto.BookingDetailsDTO;
import com.deepak.theatrecatalogueservice.dto.SeatInfoDTO;
import com.deepak.theatrecatalogueservice.entity.Seat;
import com.deepak.theatrecatalogueservice.entity.ShowDTL;
import com.deepak.theatrecatalogueservice.entity.Theatre;
import com.deepak.theatrecatalogueservice.exception.ResourceNotFoundException;
import com.deepak.theatrecatalogueservice.repo.SeatRepository;
import com.deepak.theatrecatalogueservice.repo.ShowRepository;
import com.deepak.theatrecatalogueservice.repo.TheatreRepository;
import com.deepak.theatrecatalogueservice.service.ShowService;

@Service
@Transactional
public class ShowServiceImpl implements ShowService{


	@Autowired TheatreRepository theatreRepo;
	@Autowired ShowRepository showRepo;
	
    @Autowired
    private SeatRepository seatRepo;
    
    
	@Override
	public List<Theatre> getTheatres(int cityId, int movieId) {
	return theatreRepo.findByCityIdAndMovieId(cityId, movieId);
		
	}

	@Override
	public List<ShowDTL> getShows(int theatreId) {
		
		List<ShowDTL> shows =  showRepo.findByTheatreId(theatreId);
		
		if(!shows.isEmpty()) {
			return shows;
		}else {
			 throw new ResponseStatusException(
			           HttpStatus.NOT_FOUND, "No Show Found");			
		}
		
	}

	@Override
	public BookingDetailsDTO bookSeats(SeatInfoDTO seatInfoDTO) {
        List<Seat> selectedSeats = seatRepo.findByShowIdAndSeatNameIn(seatInfoDTO.getShowId(), seatInfoDTO.getSelectedSeats());
        
        if(selectedSeats.isEmpty() || selectedSeats.size()>9) {
        	throw new ResourceNotFoundException("Select correct number of seats");
        }
        
        if (selectedSeats.stream().anyMatch(seat -> seat.getStatus() != 0)) {
        	throw new ResourceNotFoundException("Seats already booked, please check");
        }
        double totalCost = selectedSeats.stream().mapToDouble(seat -> seat.getPrice()).sum();
        selectedSeats.stream().forEach(seat -> seat.setStatus(1));
        selectedSeats.stream().forEach(seat -> seatRepo.save(seat));
        return new BookingDetailsDTO(seatInfoDTO.getShowId(), "user", totalCost, java.time.LocalDateTime.now(), selectedSeats.size());

	}

	@Override
	public Theatre addTheatre(Theatre theatre) {
		return theatreRepo.save(theatre);	
	}

	@Override
	public ShowDTL addShowDTL(ShowDTL showDTL) {
		return showRepo.save(showDTL);
	}

	@Override
	public Seat addSeat(Seat seat) {
		return seatRepo.save(seat);	
	}

	@Override
	public Theatre updateTheatre(Theatre theatre) {
		return theatreRepo.save(theatre);	
	}

	@Override
	public String deleteTheatre(int theatreId) {
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
