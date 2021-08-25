package com.github.dreamteam.controller;

import com.github.dreamteam.model.Hotel;
import com.github.dreamteam.model.Purchase;
import com.github.dreamteam.model.Trip;
import com.github.dreamteam.pojo.AddPurchaseRequest;
import com.github.dreamteam.pojo.AddTripRequest;
import com.github.dreamteam.service.hotel.HotelService;
import com.github.dreamteam.service.purchase.PurchaseService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping
    public @ResponseBody
    List<Purchase> getAllPurchases(){
        return purchaseService.getAllPurchases();
    }

    @GetMapping("/{idCode}")
    public @ResponseBody Purchase getPurchaseById(@PathVariable("idCode") long idCode) throws ObjectNotFoundException {
        return purchaseService.getPurchaseById(idCode);
    }
    @PostMapping(value = "", consumes = "application/json")
    @ResponseBody
    public Purchase save(@RequestBody @Valid AddPurchaseRequest request) {
        return purchaseService.save(request);
    }
}
