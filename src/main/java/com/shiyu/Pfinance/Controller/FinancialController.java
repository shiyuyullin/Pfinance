package com.shiyu.Pfinance.Controller;

import com.shiyu.Pfinance.Entity.Expense;
import com.shiyu.Pfinance.Entity.Income;
import com.shiyu.Pfinance.Service.FinancialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/financial")
public class FinancialController {

    @Autowired
    private FinancialService financialService;

    @GetMapping("/incomes/{userId}")
    public List<Income> getIncomes(@PathVariable Long userId) {
        return financialService.getIncomeByUser(userId);
    }

    @GetMapping("/expenses/{userId}")
    public List<Expense> getExpenses(@PathVariable Long userId) {
        return financialService.getExpensesByUser(userId);
    }

    @GetMapping("/incomes/by-month")
    public List<Income> getIncomesByYearAndMonth(@RequestParam int month, @RequestParam int year){
        return financialService.getIncomesByYearAndMonth(month, year);
    }

    @PostMapping("/income")
    public Income addIncome(@RequestBody Income income) {
        return financialService.addIncome(income);
    }

    @PostMapping("/expense")
    public Expense addExpense(@RequestBody Expense expense) {
        return financialService.addExpense(expense);
    }

}
