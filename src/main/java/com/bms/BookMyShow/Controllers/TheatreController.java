package com.bms.BookMyShow.Controllers;

import com.bms.BookMyShow.Dtos.MovieRequestDto;
import com.bms.BookMyShow.Dtos.TheatreRequestDto;
import com.bms.BookMyShow.Service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theatre")

public class TheatreController {
    @Autowired
    TheatreService theatreService;

    @PostMapping("/add")
    public ResponseEntity<String> addTheatre(@RequestBody TheatreRequestDto theatreRequestDto){
        String response = theatreService.createTheatre(theatreRequestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
