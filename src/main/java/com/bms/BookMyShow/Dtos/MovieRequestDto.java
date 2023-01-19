package com.bms.BookMyShow.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor


public class MovieRequestDto {
    private String name;
    private  int duration;
    private Date releaseDate;
}
