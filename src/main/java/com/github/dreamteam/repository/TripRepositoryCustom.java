package com.github.dreamteam.repository;

import com.github.dreamteam.model.BoardBasis;
import com.github.dreamteam.model.Trip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public class TripRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    public Page<Trip> findAll(long fromAirport,
                              long toAirportId,
                              Long hotelId,
                              LocalDate departureDate,
                              Integer numberOfDays,
                              BoardBasis type,
                              BigDecimal priceAdult,
                              BigDecimal priceChild,
                              Integer numberOfBedsAdult,
                              Integer numberOfBedsChild) {
        StringBuilder sb = new StringBuilder("SELECT t FROM Trip t ");
        sb.append(" WHERE (t.fromAirport.id = :fromAirport AND t.toAirport.id = :toAirportId) ");
        if (hotelId != null) {
            sb.append(" AND t.hotel.id = :hotelId ");
        }
        if (departureDate !=null){
            sb.append(" AND t.departureDate = :departureDate ");
        }
        if (numberOfDays !=null){
            sb.append(" AND t.numberOfDays = :numberOfDays ");
        }
        if (type !=null){
            sb.append(" AND t.type = :type ");
        }
        if (priceAdult !=null){
            sb.append(" AND t.priceAdult = :priceAdult ");
        }
        if (priceChild !=null){
            sb.append(" AND t.priceChild = :priceChild ");
        }
        if (numberOfBedsAdult !=null){
            sb.append(" AND t.numberOfBedsAdult = :numberOfBedsAdult ");
        }
        if (numberOfBedsAdult !=null){
            sb.append(" AND t.numberOfBedsChild = :numberOfBedsChild ");
        }

        TypedQuery<Trip> query = entityManager.createQuery(sb.toString(), Trip.class)
                .setParameter("fromAirport", fromAirport)
                .setParameter("toAirportId", toAirportId);
        if (hotelId != null) {
            query.setParameter("hotelId", hotelId);
        }
        if (departureDate != null) {
            query.setParameter("departureDate", departureDate);
        }
        if (numberOfDays != null) {
            query.setParameter("numberOfDays", numberOfDays);
        }
        if (type != null) {
            query.setParameter("type", type);
        }
        if (priceAdult != null) {
            query.setParameter("priceAdult", priceAdult);
        }
        if (priceChild != null) {
            query.setParameter("priceChild", priceChild);
        }
        if (numberOfBedsAdult != null) {
            query.setParameter("numberOfBedsAdult", numberOfBedsAdult);
        }
        if (numberOfBedsChild != null) {
            query.setParameter("numberOfBedsChild", numberOfBedsChild);
        }


        List<Trip> result = query
                .getResultList();
        return new PageImpl<>(result);
    }

}
