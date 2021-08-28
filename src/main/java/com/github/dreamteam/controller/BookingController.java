package com.github.dreamteam.controller;

import com.github.dreamteam.model.Booking;
import com.github.dreamteam.pojo.AddBookingRequest;
import com.github.dreamteam.service.booking.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingServiceImpl bookingService;

    @GetMapping("/{idCode}")
    public Booking getBookingById(@PathVariable("idCode") long idCode) {
        return bookingService.getBookingById(idCode);
    }

    @PostMapping(value = "", consumes = "application/json")
    public Booking save(@RequestBody @Valid AddBookingRequest request, Authentication authentication) {
        return bookingService.save(request, authentication);
    }

    @GetMapping("")
    public Page<Booking> bookings(Authentication authentication) {
        return bookingService.getCustomerBookings(authentication);
    }
}
