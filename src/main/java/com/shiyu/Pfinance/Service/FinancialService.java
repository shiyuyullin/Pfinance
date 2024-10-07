package com.shiyu.Pfinance.Service;

import com.shiyu.Pfinance.Config.MqConfig;
import com.shiyu.Pfinance.Entity.Customer;
import com.shiyu.Pfinance.Entity.Expense;
import com.shiyu.Pfinance.Entity.Income;
import com.shiyu.Pfinance.Exception.UserNotFoundException;
import com.shiyu.Pfinance.Repository.ExpenseRepository;
import com.shiyu.Pfinance.Repository.IncomeRepository;
import com.shiyu.Pfinance.Repository.CustomerRepository;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinancialService {

    private final Logger logger = LoggerFactory.getLogger(FinancialService.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    private final RabbitTemplate rabbitTemplate;

    public FinancialService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public List<Income> getIncomeByUser(Long id){
        return incomeRepository.findByCustomerId(id);
    }

    public List<Expense> getExpensesByUser(Long userId) {
        return expenseRepository.findByCustomerId(userId);
    }

    public Income addIncome(Income income) {
        Customer customer = customerRepository.findById(income.getCustomer().getId())
                .orElseThrow(() -> new UserNotFoundException("User with given ID does not exist"));
        Income savedIncome =  incomeRepository.save(income);
        rabbitTemplate.convertAndSend(MqConfig.topicExchangeName, "pfinance.notification.addIncome", new JSONObject().put("data","email:"+customer.getEmail()+",username:"+customer.getUsername()).put("pattern","add-income").toString());
        return savedIncome;
    }

    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Cacheable(key = "'incomes-by-month'", value = "#month'-'#year")
    public List<Income> getIncomesByYearAndMonth(int month, int year){
        return incomeRepository.findByMonthAndYear(month, year);
    }

}
