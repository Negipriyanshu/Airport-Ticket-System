package com.negi.repository;


import com.negi.model.City;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface CityRespository extends JpaRepository<City, Long> {

    boolean existsByCityCode(String cityCode);

    boolean existsByCityCodeAndIdNot(String cityCode, Long id);

    Page<City> findByCountryCodeIgnoreCase(String countryCode, Pageable pageable);

    @Query("""
        SELECT c FROM City c
        WHERE lower(c.name) LIKE lower(concat('%', :keyword, '%'))
           OR lower(c.cityCode) LIKE lower(concat('%', :keyword, '%'))
           OR lower(c.countryCode) LIKE lower(concat('%', :keyword, '%'))
           OR lower(c.countryName) LIKE lower(concat('%', :keyword, '%'))
           OR lower(c.regionCode) LIKE lower(concat('%', :keyword, '%'))
        """)
    Page<City> searchByKeyword(
            @Param("keyword") String keyword,
            Pageable pageable
    );

    Optional<City> findById(Long cityId);
}
