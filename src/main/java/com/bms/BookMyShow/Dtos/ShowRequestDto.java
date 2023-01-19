package com.bms.BookMyShow.Dtos;

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
public class ShowRequestDto {
    private LocalDate showDate;
    private LocalTime showTime;
    private String movieName;
    private int theatreId;
    private double multiplier;

}
