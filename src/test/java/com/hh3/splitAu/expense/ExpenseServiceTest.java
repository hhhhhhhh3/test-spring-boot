package com.hh3.splitAu.expense;

import com.hh3.splitAu.model.Expense;
import com.hh3.splitAu.repository.ExpenseRepository;
import com.hh3.splitAu.service.ExpenseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ExpenseServiceTest {

    @Mock
    private ExpenseRepository expenseRepository;
    private ExpenseService expenseService;

    @BeforeEach
    void setUp() {
        expenseService = new ExpenseService(expenseRepository);
    }

    @Test
    void getExpensesOfFriend() {
        expenseService.getExpensesByIdFriend(71L);
        verify(expenseRepository).findById(71L);
    }

    @Test
    void testGetExpenses() {
        expenseService.getExpenses();
        verify(expenseRepository).findAll();
    }

    @Test
    void addExpense() {
        Expense expense = new Expense();
        expense.setId(10l);
        expense.setDescription("testing");
        expense.setDate(new Date());
        expense.setName("test");
        expense.setAmount(10.5F);

        expenseService.addExpense(expense);

        ArgumentCaptor<Expense> expenseArgumentCaptor = ArgumentCaptor.forClass(Expense.class);
        verify(expenseRepository).save(expenseArgumentCaptor.capture());
        Expense capturedExpense = expenseArgumentCaptor.getValue();

        Assertions.assertEquals(capturedExpense,expense);
    }
}