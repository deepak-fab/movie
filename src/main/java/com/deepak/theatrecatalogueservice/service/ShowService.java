package com.deepak.theatrecatalogueservice.service;

import java.util.List;

import com.deepak.theatrecatalogueservice.dto.BookingDetailsDTO;
import com.deepak.theatrecatalogueservice.dto.SeatInfoDTO;
import com.deepak.theatrecatalogueservice.entity.Seat;
import com.deepak.theatrecatalogueservice.entity.ShowDTL;
import com.deepak.theatrecatalogueservice.entity.Theatre;

public interface ShowService {

	List<Theatre> getTheatres(int cityId, int movieId);

	List<ShowDTL> getShows(int theatreId);

	BookingDetailsDTO bookSeats(SeatInfoDTO seatInfoDTO);

	Theatre addTheatre(Theatre theatre);

	ShowDTL addShowDTL(ShowDTL showDTL);

	Seat addSeat(Seat seat);

	Theatre updateTheatre(Theatre theatre);

	String deleteTheatre(int theatreId);

}
