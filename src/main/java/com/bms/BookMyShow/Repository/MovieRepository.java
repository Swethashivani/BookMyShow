package com.bms.BookMyShow.Repository;

import com.bms.BookMyShow.Models.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {

    MovieEntity findByMovieName(String movieName);

    @Modifying
    @Query("delete from MovieEntity m where m.movieName=:name")
    void deleteByMovieName(String name);
}
