package com.bms.BookMyShow.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetShowRequestDto {
    private LocalDate startDate;
    private LocalDate endDate;

    private LocalTime startTime;

    private LocalTime endTime;
    private int movieId;



}
