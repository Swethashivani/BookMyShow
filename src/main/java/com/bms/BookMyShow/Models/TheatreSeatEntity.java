package com.bms.BookMyShow.Models;

import com.bms.BookMyShow.Enums.SeatType;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Theatre_Seats")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TheatreSeatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String seatNo;
    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;
    private int rate;

    public TheatreSeatEntity(String seatNo, SeatType seatType, int rate){
        this.seatNo=seatNo;
        this.seatType=seatType;
        this.rate=rate;
    }

    @ManyToOne
    @JoinColumn
    private TheatreEntity theatre;
}
