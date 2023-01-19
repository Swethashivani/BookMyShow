package com.bms.BookMyShow.Service;

import com.bms.BookMyShow.Dtos.TheatreRequestDto;
import com.bms.BookMyShow.Enums.SeatType;
import com.bms.BookMyShow.Models.TheatreEntity;
import com.bms.BookMyShow.Models.TheatreSeatEntity;
import com.bms.BookMyShow.Repository.TheatreRepository;
import com.bms.BookMyShow.Repository.TheatreSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheatreService {
    @Autowired
    TheatreRepository theatreRepository;
    @Autowired
    TheatreSeatRepository theatreSeatRepository;

    public String createTheatre(TheatreRequestDto theatreRequestDto) {
        TheatreEntity theatre = TheatreEntity.builder()
                .name(theatreRequestDto.getName())
                .city(theatreRequestDto.getCity())
                .address(theatreRequestDto.getAddress())
                .build();
        try {
            List<TheatreSeatEntity> theatreSeats = createTheatreSeats();
            theatre.setTheatreSeatEntityList(theatreSeats);
            for (TheatreSeatEntity theatreSeat : theatreSeats) {
                theatreSeat.setTheatre(theatre);
            }
            theatreRepository.save(theatre);
        } catch (Exception e) {
            return "Theatre couldn't be created";
        }
        return "Theatre is created";
    }

    public List<TheatreSeatEntity> createTheatreSeats() {
        List<TheatreSeatEntity> seats = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            char ch = (char) ('A' + i);
            String seatNo = "1" + ch;
            TheatreSeatEntity theatreSeat = new TheatreSeatEntity(seatNo, SeatType.CLASSIC, 100);
            seats.add(theatreSeat);
        }
        for (int i = 0; i < 5; i++) {
            char ch = (char) ('A' + i);
            String seatNo = "2" + ch;
            TheatreSeatEntity theatreSeat = new TheatreSeatEntity(seatNo, SeatType.PLATINUM, 200);
            seats.add(theatreSeat);
        }

//        TheatreSeatEntity theatreSeat1 = new TheatreSeatEntity("1A", SeatType.CLASSIC, 100);
//        TheatreSeatEntity theatreSeat2 = new TheatreSeatEntity("1B", SeatType.CLASSIC, 100);
//        TheatreSeatEntity theatreSeat3 = new TheatreSeatEntity("1C", SeatType.CLASSIC, 100);
//        TheatreSeatEntity theatreSeat4 = new TheatreSeatEntity("1D", SeatType.CLASSIC, 100);
//        TheatreSeatEntity theatreSeat5 = new TheatreSeatEntity("1E", SeatType.CLASSIC, 100);
//        TheatreSeatEntity theatreSeat6 = new TheatreSeatEntity("2A", SeatType.PLATINUM, 200);
//        TheatreSeatEntity theatreSeat7 = new TheatreSeatEntity("2B", SeatType.PLATINUM, 200);
//        TheatreSeatEntity theatreSeat8 = new TheatreSeatEntity("2C", SeatType.PLATINUM, 200);
//        TheatreSeatEntity theatreSeat9 = new TheatreSeatEntity("2D", SeatType.PLATINUM, 200);
//        TheatreSeatEntity theatreSeat10 = new TheatreSeatEntity("2E", SeatType.PLATINUM, 200);
        theatreSeatRepository.saveAll(seats);
        return seats;
    }
}
