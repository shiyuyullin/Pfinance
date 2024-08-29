package com.shiyu.Pfinance.Repository;

import com.shiyu.Pfinance.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByCustomerId(Long id);


}
