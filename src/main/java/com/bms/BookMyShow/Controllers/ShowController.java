package com.bms.BookMyShow.Controllers;

import com.bms.BookMyShow.Dtos.ShowRequestDto;
import com.bms.BookMyShow.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}

