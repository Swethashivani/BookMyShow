package com.bms.BookMyShow.Service;

import com.bms.BookMyShow.Dtos.GetShowRequestDto;
import com.bms.BookMyShow.Dtos.ShowRequestDto;
import com.bms.BookMyShow.Models.*;
import com.bms.BookMyShow.Repository.MovieRepository;
import com.bms.BookMyShow.Repository.ShowRepository;
import com.bms.BookMyShow.Repository.ShowSeatRepository;
import com.bms.BookMyShow.Repository.TheatreRepository;
import com.bms.BookMyShow.ResponseDtos.GetShowResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ShowService {
    @Autowired
    ShowRepository showRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheatreRepository theatreRepository;

    @Autowired
    ShowSeatRepository showSeatRepository;

    public String addShow(ShowRequestDto showRequestDto) {

        //Show Entity
        ShowEntity showEntity = ShowEntity.builder().showDate(showRequestDto.getShowDate()).showTime(showRequestDto.getShowTime()).multiplier(showRequestDto.getMultiplier()).build();


        //You need to get the movieRepo
        MovieEntity movieEntity = movieRepository.findByMovieName(showRequestDto.getMovieName());

        //You need to get the theater Repository

        TheatreEntity theaterEntity = theatreRepository.findById(showRequestDto.getTheatreId()).get();

        showEntity.setTheatre(theaterEntity);
        showEntity.setMovie(movieEntity);

        movieEntity.getListOfShows().add(showEntity);
        theaterEntity.getListOfShows().add(showEntity);

        List<ShowSeatEntity> seatEntityList = createShowSeats(theaterEntity.getTheatreSeatEntityList());

        showEntity.setListOfSeats(seatEntityList);

        //For each ShowSeat : we need to mark that to which show it belongs (foreign key will be filled )
        for (ShowSeatEntity showSeat : seatEntityList) {
            showSeat.setShow(showEntity);
        }

        movieRepository.save(movieEntity);
        theatreRepository.save(theaterEntity);
        //showRepository.save(showEntity); this doesn't need to be called bcz parent save function is being called.

        return "Show added successfully";

    }

    private List<ShowSeatEntity> createShowSeats(List<TheatreSeatEntity> theatreSeatEntityList) {
        List<ShowSeatEntity> seats = new ArrayList<>();
        for (TheatreSeatEntity theatreSeat : theatreSeatEntityList) {
            ShowSeatEntity showSeat = ShowSeatEntity.builder().seatNo(theatreSeat.getSeatNo())
                    .seatType(theatreSeat.getSeatType()).build();
            seats.add(showSeat);
        }
        showSeatRepository.saveAll(seats);
        return seats;
    }


    public String deleteShow(int showId) {
        showRepository.deleteById(showId);
        return "deleted successfully";
    }


    public List<GetShowResponseDto> getShowsForTimeRange(GetShowRequestDto getShowRequestDto) {
        List<ShowEntity> shows =  showRepository.getShowsByMovieIdAndDateRange( LocalDateTime.of(getShowRequestDto.getStartDate(),
                getShowRequestDto.getStartTime()),  LocalDateTime.of(getShowRequestDto.getEndDate(),
                getShowRequestDto.getEndTime()), getShowRequestDto.getMovieId());
        List<GetShowResponseDto> showResponseDtos = new ArrayList<>();
        for (ShowEntity show:shows
             ) {
            GetShowResponseDto getShowResponseDto = GetShowResponseDto.builder().id(show.getId())
                            .showDate(show.getShowDate()).showTime(show.getShowTime())
                            .movieName(show.getMovie().getMovieName()).TheatreName(show.getTheatre().getName()).build();
            showResponseDtos.add(getShowResponseDto);
        }
        return showResponseDtos;
    }
}
