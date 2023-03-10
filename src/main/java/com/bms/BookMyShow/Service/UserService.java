package com.bms.BookMyShow.Service;

import com.bms.BookMyShow.Dtos.UserRequestDto;
import com.bms.BookMyShow.Models.ShowEntity;
import com.bms.BookMyShow.Models.TicketEntity;
import com.bms.BookMyShow.Models.UserEntity;
import com.bms.BookMyShow.Repository.UserRepository;
import com.bms.BookMyShow.ResponseDtos.AllTicketsResponseDto;
import com.bms.BookMyShow.ResponseDtos.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;

    public String createUser(UserRequestDto userRequestDto) {
        UserEntity userEntity = UserEntity.builder().name(userRequestDto.getName()).mobile(userRequestDto.getMobile()).build();

        try {
            userRepository.save(userEntity);
        } catch (Exception e) {
            return "user couldn't be created";
        }
        return "successfully created user";
    }


    public List<UserResponseDto> getAllUsers() {
        List<UserEntity> userEntityList = new ArrayList<>();
        userEntityList = userRepository.findAll();
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        for (UserEntity user: userEntityList) {
            UserResponseDto userResponseDto = UserResponseDto.builder().name(user.getName())
                    .mobile(user.getMobile()).build();
            userResponseDtoList.add(userResponseDto);
        }
        return userResponseDtoList;
    }

    public UserResponseDto getUserByName(String name){

       UserEntity userEntity= userRepository.findByName(name);
       UserResponseDto userResponseDto = UserResponseDto.builder().name(userEntity.getName())
               .mobile(userEntity.getMobile()).build();
       return userResponseDto;
    }


    public List<AllTicketsResponseDto> getAllTickets(int userId) {
        UserEntity user = userRepository.findById(userId).get();
        List<TicketEntity> ticketEntityList = user.getListOfTickets();
        List<AllTicketsResponseDto> ticketsResponse = new ArrayList<>();
        for (TicketEntity ticket: ticketEntityList) {
            ShowEntity showEntity = ticket.getShow();
            AllTicketsResponseDto allTicketsresponseDto = AllTicketsResponseDto.builder().MovieName(showEntity.getMovie().getMovieName())
                    .bookedSeats(ticket.getAllotedSeats())
                    .showTime(showEntity.getShowTime())
                    .showDate(showEntity.getShowDate())
                    .theatreName(showEntity.getTheatre().getName())
                    .build();
            ticketsResponse.add(allTicketsresponseDto);
        }
        return ticketsResponse;
        }


}
