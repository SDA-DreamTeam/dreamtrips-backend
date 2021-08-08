package com.github.dreamteam.repository;

import com.github.dreamteam.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {

}
