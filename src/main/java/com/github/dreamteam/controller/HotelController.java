package com.github.dreamteam.controller;

import com.github.dreamteam.model.Hotel;
import com.github.dreamteam.pojo.AddHotelRequest;
import com.github.dreamteam.service.hotel.HotelService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping
    public @ResponseBody
    List<Hotel> getAllHotels(){
        return hotelService.getAllHotels();
    }

    @GetMapping("/{idCode}")
    public @ResponseBody Hotel getHotelById(@PathVariable("idCode") long idCode) throws ObjectNotFoundException {
        return hotelService.getHotelById(idCode);
    }
    @PostMapping(value = "", consumes = "application/json")
    @ResponseBody
    public Hotel save(@RequestBody @Valid AddHotelRequest request) {
        return hotelService.save(request);
    }
}
