package com.shiyu.Pfinance.Service;

import com.shiyu.Pfinance.Entity.Expense;
import com.shiyu.Pfinance.Entity.Income;
import com.shiyu.Pfinance.Repository.ExpenseRepository;
import com.shiyu.Pfinance.Repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinancialService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Income> getIncomeByUser(Long id){
        return incomeRepository.findByUserId(id);
    }

    public List<Expense> getExpensesByUser(Long userId) {
        return expenseRepository.findByUserId(userId);
    }

    public Income addIncome(Income income) {
        return incomeRepository.save(income);
    }

    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

}
