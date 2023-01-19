package com.bms.BookMyShow.ResponseDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class MovieResponseDto {
    private String movieName;
    private  int duration;
    private Date releaseDate;
}
