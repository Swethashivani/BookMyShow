package com.bms.BookMyShow.Controllers;

import com.bms.BookMyShow.Dtos.BookTicketRequestDto;
import com.bms.BookMyShow.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;
    @PostMapping("/book")
    public ResponseEntity<String> bookTicket(@RequestBody BookTicketRequestDto bookTicketRequestDto){
        try {
            return new ResponseEntity<>(ticketService.bookTicket(bookTicketRequestDto), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Requested seats not available" , HttpStatus.OK);
        }
    }

}
