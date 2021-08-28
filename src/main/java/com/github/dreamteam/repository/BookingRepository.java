package com.github.dreamteam.repository;

import com.github.dreamteam.model.Booking;
import com.github.dreamteam.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {


}
