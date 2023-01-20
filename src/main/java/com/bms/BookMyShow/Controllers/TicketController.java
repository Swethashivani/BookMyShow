package com.bms.BookMyShow.Controllers;

import com.bms.BookMyShow.Dtos.BookTicketRequestDto;
import com.bms.BookMyShow.Dtos.CancelTicketRequestDto;
import com.bms.BookMyShow.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/cancel")
    public ResponseEntity<String> cancelTicket(@RequestBody CancelTicketRequestDto cancelTicketRequestDto){
        try {
            return new ResponseEntity<>(ticketService.cancelTicket(cancelTicketRequestDto), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Seats could not be cancelled" , HttpStatus.OK);
        }
    }

}
