package com.bms.BookMyShow.Dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BookTicketRequestDto {
    private List<String> requestSeats;
    private int showId;
    private int userId;


}
