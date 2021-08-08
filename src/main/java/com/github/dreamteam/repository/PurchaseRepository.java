package com.github.dreamteam.repository;

import com.github.dreamteam.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, String> {

}
