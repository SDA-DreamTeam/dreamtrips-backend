package com.github.dreamteam.controller;

import com.github.dreamteam.model.Country;
import com.github.dreamteam.model.Hotel;
import com.github.dreamteam.service.Country.CountryService;
import com.github.dreamteam.service.Hotel.HotelService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping
    public @ResponseBody
    List<Hotel> getAllCountries(){
        return hotelService.getAllHotels();
    }

    @GetMapping("/{idCode}")
    public @ResponseBody Hotel getHotelById(@PathVariable("idCode") long idCode) throws ObjectNotFoundException {
        return hotelService.getHotelById(idCode);
    }
}
