package com.bms.BookMyShow.Service;

import com.bms.BookMyShow.Dtos.BookTicketRequestDto;
import com.bms.BookMyShow.Models.ShowEntity;
import com.bms.BookMyShow.Models.ShowSeatEntity;
import com.bms.BookMyShow.Models.TicketEntity;
import com.bms.BookMyShow.Models.UserEntity;
import com.bms.BookMyShow.Repository.ShowRepository;
import com.bms.BookMyShow.Repository.TicketRepository;
import com.bms.BookMyShow.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    public String bookTicket(BookTicketRequestDto bookTicketRequestDto) throws Exception {
        List<String> requestedSeats = bookTicketRequestDto.getRequestSeats();
        ShowEntity showEntity = showRepository.findById(bookTicketRequestDto.getShowId()).get();
        UserEntity userEntity = userRepository.findById(bookTicketRequestDto.getUserId()).get();

        //get shows seats from show-entity
        List<ShowSeatEntity> showSeats = showEntity.getListOfSeats();
        List<ShowSeatEntity> bookedSeats = new ArrayList<>();
        //validate
        for (ShowSeatEntity showSeat : showSeats) {
            String seatNo = showSeat.getSeatNo();
            if (!showSeat.isBooked() && requestedSeats.contains(seatNo)) {
                bookedSeats.add(showSeat);
            }

        }
        if (bookedSeats.size() != requestedSeats.size()) {
            throw new Exception("Requested seats is not available");
        }
        TicketEntity ticketEntity = new TicketEntity();
        double totalAmount = 0;
        double multiplier = showEntity.getMultiplier();
        int rate = 0;
        String allotedseats = "";
        for (ShowSeatEntity bookedSeat : bookedSeats) {
            bookedSeat.setBooked(true);
            bookedSeat.setBookedAt(new Date());
            bookedSeat.setShow(showEntity);
            bookedSeat.setTicket(ticketEntity);
            String seatNo = bookedSeat.getSeatNo();
            allotedseats = allotedseats + seatNo + ",";
            if (seatNo.charAt(0) == '1')
                rate = 100;
            else
                rate = 200;
            totalAmount = totalAmount + (multiplier * rate);
        }
        ticketEntity.setBooked_at(new Date());
        ticketEntity.setAmount((int) totalAmount);
        ticketEntity.setShow(showEntity);
        ticketEntity.setUser(userEntity);
        ticketEntity.setBookedSeats(bookedSeats);
        ticketEntity.setAllotedSeats(allotedseats);

        ticketRepository.save(ticketEntity);

        return "Tickets are successfully generated";
    }
}
