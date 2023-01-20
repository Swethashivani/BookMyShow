package com.bms.BookMyShow.Repository;

import com.bms.BookMyShow.Dtos.UserRequestDto;
import com.bms.BookMyShow.Models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByName(String name);


}
