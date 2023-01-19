package com.bms.BookMyShow.Controllers;

import com.bms.BookMyShow.Dtos.MovieRequestDto;
import com.bms.BookMyShow.Models.MovieEntity;
import com.bms.BookMyShow.Models.UserEntity;
import com.bms.BookMyShow.ResponseDtos.MovieResponseDto;
import com.bms.BookMyShow.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<String> addMovie(@RequestBody MovieRequestDto movieRequestDto) {
        String response = movieService.createMovie(movieRequestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<MovieResponseDto> getMovieByName(@RequestParam String movieName) {
        return new ResponseEntity<>(movieService.getMovieByName(movieName), HttpStatus.OK);
    }

    @GetMapping("/allMovies")
    public ResponseEntity<List<MovieResponseDto>> getAllMovies() {
        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
    }
    @DeleteMapping("/deleteMovie")
    public ResponseEntity<String> deleteMovieById(int id){
        return new ResponseEntity<>(movieService.deleteMovie(id), HttpStatus.OK);
    }

    @DeleteMapping("/deleteMovieByName")
    public ResponseEntity<String> deleteMovieByName(String name){
        return new ResponseEntity<>(movieService.deleteMovieByName(name), HttpStatus.OK);
    }



}
