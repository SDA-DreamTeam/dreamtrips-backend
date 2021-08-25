package com.github.dreamteam.service.purchase;

import com.github.dreamteam.model.Hotel;
import com.github.dreamteam.model.Purchase;
import com.github.dreamteam.model.Trip;
import com.github.dreamteam.pojo.AddHotelRequest;
import com.github.dreamteam.pojo.AddPurchaseRequest;
import javassist.tools.rmi.ObjectNotFoundException;

import java.util.List;

public interface PurchaseService {
    Purchase getPurchaseById(long id) throws ObjectNotFoundException;

    List<Purchase> getAllPurchases();

    Purchase save(AddPurchaseRequest request);
}
