package com.bms.BookMyShow.Service;

import com.bms.BookMyShow.Dtos.MovieRequestDto;
import com.bms.BookMyShow.Models.MovieEntity;
import com.bms.BookMyShow.Models.UserEntity;
import com.bms.BookMyShow.Repository.MovieRepository;
import com.bms.BookMyShow.ResponseDtos.MovieResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public String createMovie(MovieRequestDto movieRequestDto) {
        MovieEntity movieEntity = MovieEntity.builder().movieName(movieRequestDto.getName()).duration(movieRequestDto.getDuration()).releaseDate(movieRequestDto.getReleaseDate()).build();
        try {
            movieRepository.save(movieEntity);
        } catch (Exception e) {
            return "Movie couldn't be created";
        }
        return "Movie successfully created";
    }

    public List<MovieResponseDto> getAllMovies() {
        List<MovieEntity> movieEntities = new ArrayList<>();
        List<MovieResponseDto> movieResponseDtoList = new ArrayList<>();
        movieEntities = movieRepository.findAll();
        for (MovieEntity movieEntity : movieEntities) {
            MovieResponseDto movieResponseDto = MovieResponseDto.builder().movieName(movieEntity.getMovieName())
                    .duration(movieEntity.getDuration()).releaseDate(movieEntity.getReleaseDate()).build();
            movieResponseDtoList.add(movieResponseDto);

        }
        return movieResponseDtoList;
    }

    public MovieResponseDto getMovieByName(String movieName) {

        MovieEntity movieEntity = movieRepository.findByMovieName(movieName);
        return MovieResponseDto.builder().movieName(movieEntity.getMovieName())
                .duration(movieEntity.getDuration()).releaseDate(movieEntity.getReleaseDate()).build();

    }

    public String deleteMovie(int id) {
        movieRepository.deleteById(id);
        return "Successfully deleted the movie ";
    }

    public String deleteMovieByName(String name) {
        movieRepository.deleteByMovieName(name);
        return "Successfully deleted the movie ";
    }
}
