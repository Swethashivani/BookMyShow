package com.bms.BookMyShow.ResponseDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetShowResponseDto {
    private int id;

    private LocalDate showDate;
    private LocalTime showTime;

    private String movieName;
    private String TheatreName;


}
