package com.shiyu.Pfinance.Service;

import com.shiyu.Pfinance.Entity.Customer;
import com.shiyu.Pfinance.Entity.Expense;
import com.shiyu.Pfinance.Entity.Income;
import com.shiyu.Pfinance.Exception.UserNotFoundException;
import com.shiyu.Pfinance.Repository.ExpenseRepository;
import com.shiyu.Pfinance.Repository.IncomeRepository;
import com.shiyu.Pfinance.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinancialService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Income> getIncomeByUser(Long id){
        return incomeRepository.findByCustomerId(id);
    }

    public List<Expense> getExpensesByUser(Long userId) {
        return expenseRepository.findByCustomerId(userId);
    }

    public Income addIncome(Income income) {
        Customer customer = customerRepository.findById(income.getCustomer().getId())
                .orElseThrow(() -> new UserNotFoundException("User with given ID does not exist"));
        return incomeRepository.save(income);
    }

    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Cacheable(key = "'incomes-by-month'", value = "#month'-'#year")
    public List<Income> getIncomesByYearAndMonth(int month, int year){
        return incomeRepository.findByMonthAndYear(month, year);
    }

}
