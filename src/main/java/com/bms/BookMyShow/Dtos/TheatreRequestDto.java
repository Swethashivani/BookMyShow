package com.bms.BookMyShow.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TheatreRequestDto {
    private String name;
    private String city;
    private String address;


}
