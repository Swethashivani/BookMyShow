package com.bms.BookMyShow.Repository;
import com.bms.BookMyShow.Models.ShowSeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeatEntity, Integer> {

}
