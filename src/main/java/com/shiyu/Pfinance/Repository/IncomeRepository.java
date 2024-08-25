package com.shiyu.Pfinance.Repository;

import com.shiyu.Pfinance.Entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Long> {

    List<Income> findByUserId(Long id);


}
