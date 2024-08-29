package com.shiyu.Pfinance.Repository;

import com.shiyu.Pfinance.Entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Long> {

    List<Income> findByCustomerId(Long id);

    @Query("SELECT i FROM Income i WHERE MONTH(i.dateReceived) = :month AND YEAR(i.dateReceived) = :year")
    List<Income> findByMonthAndYear(@Param("month") int month, @Param("year") int year);


}
