package com.bms.BookMyShow.Models;

import javax.persistence.*;

import com.bms.BookMyShow.Enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Tickets")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String allotedSeats;
    private int amount;
    private Date booked_at;


    @Enumerated(value = EnumType.STRING)
    private TicketStatus status;
    @ManyToOne
    @JoinColumn
    private UserEntity user;

    @ManyToOne
    @JoinColumn
    private ShowEntity show;

    @OneToMany(mappedBy= "ticket", cascade = CascadeType.ALL)
    private List<ShowSeatEntity> bookedSeats;


}
