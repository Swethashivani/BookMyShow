package com.bms.BookMyShow.Repository;

import com.bms.BookMyShow.Models.MovieEntity;
import com.bms.BookMyShow.Models.ShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<ShowEntity, Integer> {

    @Query(value = "select * from shows where cast(concat(show_date, ' ', show_time) as datetime) >=:startDateTime and cast(concat(show_date, ' ', show_time) as datetime) <=:endDateTime and movie_id=:movieId", nativeQuery = true)
    List<ShowEntity> getShowsByMovieIdAndDateRange(LocalDateTime startDateTime, LocalDateTime endDateTime, int movieId);

}
