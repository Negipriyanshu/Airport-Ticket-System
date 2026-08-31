package com.negi.repository;

import com.negi.model.City;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRespository extends JpaRepository<City,Long> {

    boolean existsByCityCode(String cityCode);

    boolean existByCityCodeAndIdNot(String coityCode,Long id);

    Page<City> findByCountryCodeIgnoreCase(String countryCode, Pageable pageable);

    @Query("""
        SELECT c 
        FROM City c
        WHERE lower(c.name) like lower(concate('%', :keyword, '%'))
            OR lower(c.cityCode) like lower(concate('%', :keyword, '%'))
            OR lower(c.countryCode) like lower(concate('%', :keyword, '%'))
            OR lower(c.countryName) like lower(concate('%', :keyword, '%'))
            OR lower(c.regionCode) like lower(concate('%', :keyword, '%'))
        """)
    Page<City> searchByKeyword(String keyword, Pageable pageable);

    boolean existByCityCode(@NotBlank(message = "City name is required") @Size(max=100) String cityCode);
}
