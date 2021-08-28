package com.github.dreamteam.service.booking;

import com.github.dreamteam.exception.NotFoundException;
import com.github.dreamteam.model.Booking;
import com.github.dreamteam.model.BookingStatus;
import com.github.dreamteam.model.Trip;
import com.github.dreamteam.model.User;
import com.github.dreamteam.pojo.AddBookingRequest;
import com.github.dreamteam.pojo.FindBookingRequest;
import com.github.dreamteam.repository.BookingRepository;
import com.github.dreamteam.repository.BookingRepositoryCustom;
import com.github.dreamteam.repository.TripRepository;
import com.github.dreamteam.repository.UserRepository;
import com.github.dreamteam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingServiceImpl {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BookingRepositoryCustom bookingRepositoryCustom;

    // @Autowired
    //private Clock clock;


    public Booking getBookingById(long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new NotFoundException("Unable to find such purchase " + bookingId));
    }


    public List<Booking> getAllBookings() {
        return null;
    }


    public Page<Booking> getCustomerBookings(Authentication authentication) {
        long userId = userService.getUser(authentication.getName()).getId();
        return bookingRepositoryCustom.findBookingByUser(userId);
    }

    @Transactional
    public Booking save(AddBookingRequest request, Authentication authentication) {
        final User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException(authentication.getName()));
        final Trip trip = tripRepository.findById(request.getTripId())
                .orElseThrow(() -> new NotFoundException("Trip not found"));

        final Booking booking = new Booking();
        booking.setStatus(BookingStatus.NEW);
        booking.setTrip(trip);
        booking.setUser(user);
        //booking.setCreated(LocalDateTime.now(clock));
        booking.setParticipantsDetails(request.getParticipantsDetails());
        return bookingRepository.save(booking);
    }

}
