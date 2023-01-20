package com.bms.BookMyShow.ResponseDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AllTicketsResponseDto {
    private String MovieName;
    private String bookedSeats;
    private LocalDate showDate;
    private LocalTime showTime;
    private String theatreName;

}
