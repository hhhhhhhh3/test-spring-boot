package com.hh3.splitAu.service;

import com.hh3.splitAu.model.Expense;
import com.hh3.splitAu.repository.ExpenseRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> getExpensesByIdFriend(Long idFriend) {
        return this.expenseRepository.findById(idFriend).stream().filter(expense -> expense.getFriend().getId().equals(idFriend)).collect(Collectors.toList());
    }

    public List<Expense> getExpenses() {
        return this.expenseRepository.findAll();
    }

    public void addExpense (@RequestBody Expense expense) {
        this.expenseRepository.save(expense);
    }

    public void deleteExpense (Long idExpense) {
        this.expenseRepository.deleteById(idExpense);
    }

}


