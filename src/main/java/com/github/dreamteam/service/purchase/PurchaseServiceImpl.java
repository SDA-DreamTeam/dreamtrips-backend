package com.github.dreamteam.service.purchase;

import com.github.dreamteam.exception.NotFoundException;
import com.github.dreamteam.model.City;
import com.github.dreamteam.model.Hotel;
import com.github.dreamteam.model.Purchase;
import com.github.dreamteam.model.Trip;
import com.github.dreamteam.pojo.AddPurchaseRequest;
import com.github.dreamteam.repository.PurchaseRepository;
import com.github.dreamteam.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService{
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private TripRepository tripRepository;

    @Override
    public Purchase getPurchaseById(long purchaseId) {
        return purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new NotFoundException("Unable to find such purchase " + purchaseId));
    }

    @Override
    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    @Override
    public Purchase save(AddPurchaseRequest request) {
        Trip trip = tripRepository.findById(request.getTripId())
                .orElseThrow(() -> new NotFoundException("Trip not found"));
        BigDecimal amount=new BigDecimal(5400);
        Purchase purchase=new Purchase();
        purchase.setTrip(trip);
        purchase.setAmount(amount);
        return purchaseRepository.save(purchase);
    }
}
