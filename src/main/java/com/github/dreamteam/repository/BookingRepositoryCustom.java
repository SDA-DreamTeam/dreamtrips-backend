package com.github.dreamteam.repository;

import com.github.dreamteam.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class BookingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    public Page<Booking> findBookingByUser(long userId){
        String query="SELECT b FROM Booking b WHERE b.user.id = :userId";
        TypedQuery<Booking> newQuery = entityManager.createQuery(query, Booking.class)
                .setParameter("userId",userId);
        List<Booking> result = newQuery
                .getResultList();
        return new PageImpl<>(result);
    }
}
