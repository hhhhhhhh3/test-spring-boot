package com.hh3.splitAu.controller;

import com.hh3.splitAu.model.Expense;
import com.hh3.splitAu.model.Friend;
import com.hh3.splitAu.service.ExpenseService;
import com.hh3.splitAu.service.FriendService;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class ExpenseController {

    private final ExpenseService expenseService;
    private final FriendService friendService;

    public ExpenseController(ExpenseService expenseService, FriendService friendService) {
        this.expenseService = expenseService;
        this.friendService = friendService;
    }

    @GetMapping("/expense/friend/{id}")
    public List<Expense> getExpensesOfFriend(@PathVariable Long id) {
        return this.expenseService.getExpensesByIdFriend(id);
    }

    @GetMapping("/expense")
    public List<Expense> getExpenses() {
        return this.expenseService.getExpenses().stream()
                .sorted(Comparator.comparing(Expense::getDate))
                .collect(Collectors.toList());
    }

    @PostMapping ("/expense/{id}")
    public void addExpense(@PathVariable Long id, @RequestBody Expense expense) {
        Friend friend = this.friendService.getFriend(id).get();
        expense.setName(friend.getName());
        expense.setFriend(friend);
        this.expenseService.addExpense(expense);
    }
}