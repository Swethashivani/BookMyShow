package com.bms.BookMyShow.Controllers;

import com.bms.BookMyShow.Dtos.GetShowRequestDto;
import com.bms.BookMyShow.Dtos.ShowRequestDto;
import com.bms.BookMyShow.Models.ShowEntity;
import com.bms.BookMyShow.ResponseDtos.GetShowResponseDto;
import com.bms.BookMyShow.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping("/add")
    public ResponseEntity<String> addShow(@RequestBody ShowRequestDto showRequestDto){
        return new ResponseEntity<>(showService.addShow(showRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteShowById(@RequestParam int showId){
        return new ResponseEntity<>(showService.deleteShow(showId), HttpStatus.OK);

    }

    @GetMapping("/getShowsForTimeRange")
    public ResponseEntity<List<GetShowResponseDto>> getShowsForTimeRange(@RequestParam String startDate, @RequestParam String startTime, @RequestParam String endTime, @RequestParam String endDate, @RequestParam int movieid){
        LocalDate startingDate = LocalDate.parse(startDate);
        LocalDate endingDate = LocalDate.parse(endDate);
        LocalTime startingTime = LocalTime.parse(startTime);
        LocalTime endingTime = LocalTime.parse(endTime);
        GetShowRequestDto getShowRequestDto =  new GetShowRequestDto(startingDate, endingDate, startingTime, endingTime, movieid);
        return new ResponseEntity<>(showService.getShowsForTimeRange(getShowRequestDto), HttpStatus.OK);
    }
}

