package com.github.dreamteam.repository;

import com.github.dreamteam.model.BoardBasis;
import com.github.dreamteam.model.Trip;
import com.github.dreamteam.pojo.Tuple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TripRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    public Page<Trip> findAll(Long fromCountry,
                              Long fromAirport,
                              Long toCountry,
                              Long toAirport,
                              Long hotelId,
                              LocalDate departureDate,
                              Integer numberOfDays,
                              BoardBasis type,
                              BigDecimal priceAdult,
                              BigDecimal priceChild,
                              Integer numberOfBedsAdult,
                              Integer numberOfBedsChild,
                              PageRequest pageRequest) {
        StringBuilder sb = new StringBuilder("SELECT t FROM Trip t ");
        Map<String, Tuple<String, Object>> params = new HashMap<>();
        Optional.ofNullable(fromCountry).ifPresent(v -> params.put("t.fromAirport.city.country.id", Tuple.of("fromCountry", v)));
        Optional.ofNullable(fromAirport).ifPresent(v -> params.put("t.fromAirport.id", Tuple.of("fromAirport", v)));
        Optional.ofNullable(toCountry).ifPresent(v -> params.put("t.toAirport.city.country.id", Tuple.of("toCountry", v)));
        Optional.ofNullable(toAirport).ifPresent(v -> params.put("t.toAirport.id", Tuple.of("toAirport", v)));
        Optional.ofNullable(hotelId).ifPresent(v -> params.put("t.hotel.id", Tuple.of("hotelId", v)));
        Optional.ofNullable(departureDate).ifPresent(v -> params.put("t.departureDate", Tuple.of("departureDate", v)));
        Optional.ofNullable(numberOfDays).ifPresent(v -> params.put("t.numberOfDays", Tuple.of("numberOfDays", v)));
        Optional.ofNullable(type).ifPresent(v -> params.put("t.type", Tuple.of("type", v)));
        Optional.ofNullable(priceAdult).ifPresent(v -> params.put("t.priceAdult", Tuple.of("priceAdult", v)));
        Optional.ofNullable(priceChild).ifPresent(v -> params.put("t.priceChild", Tuple.of("priceChild", v)));
        Optional.ofNullable(numberOfBedsAdult).ifPresent(v -> params.put("t.numberOfBedsAdult", Tuple.of("numberOfBedsAdult", v)));
        Optional.ofNullable(numberOfBedsChild).ifPresent(v -> params.put("t.numberOfBedsChild", Tuple.of("numberOfBedsChild", v)));

        List<String> lines = params.entrySet().stream()
                .map(e -> e.getKey() + " = :" + e.getValue().getA())
                .collect(Collectors.toList());
        // 0 - t.fromAirport.city.country.id = :fromCountry
        // 1 - t.departureDate = :departureDate
        String whereClause = String.join(" AND ", lines);
        // t.fromAirport.city.country.id = :fromCountry AND t.departureDate = :departureDate
        if (!whereClause.isEmpty()) {
            whereClause = " WHERE " + whereClause;
        }
        TypedQuery<Trip> query = entityManager.createQuery(sb.append(whereClause).toString(), Trip.class);
        for (Map.Entry<String, Tuple<String, Object>> entry : params.entrySet()) {
            query.setParameter(entry.getValue().getA(), entry.getValue().getB());
        }
        List<Trip> result = query
                .setFirstResult((int) pageRequest.getOffset())
                .setMaxResults(pageRequest.getPageSize())
                .getResultList();

        StringBuilder countSb = new StringBuilder("SELECT count(t) FROM Trip t ");
        TypedQuery<Long> countQuery = entityManager.createQuery(countSb.append(whereClause).toString(), Long.class);
        for (Map.Entry<String, Tuple<String, Object>> entry : params.entrySet()) {
            countQuery.setParameter(entry.getValue().getA(), entry.getValue().getB());
        }
        Long total = countQuery.getSingleResult();

        return new PageImpl<>(result, pageRequest, total);
    }

}
