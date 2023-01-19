package com.bms.BookMyShow.Controllers;

import com.bms.BookMyShow.Dtos.UserRequestDto;
import com.bms.BookMyShow.Models.UserEntity;
import com.bms.BookMyShow.ResponseDtos.UserResponseDto;
import com.bms.BookMyShow.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody UserRequestDto userRequestDto) {
        return new ResponseEntity<>(userService.createUser(userRequestDto), HttpStatus.OK);
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<UserResponseDto> getUserByName(@RequestParam String name) {
        return new ResponseEntity<>(userService.getUserByName(name), HttpStatus.OK);
    }

}