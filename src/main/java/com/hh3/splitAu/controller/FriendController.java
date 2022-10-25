package com.hh3.splitAu.controller;

import com.hh3.splitAu.model.Expense;
import com.hh3.splitAu.model.Friend;
import com.hh3.splitAu.model.FriendAdapter;
import com.hh3.splitAu.service.ExpenseService;
import com.hh3.splitAu.service.FriendService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class FriendController {
    private FriendService friendService;
    private ExpenseService expenseService;
    private Float totalDue;

    public FriendController(FriendService userService, ExpenseService expenseService) {
        this.totalDue = 0.0F;
        this.friendService = userService;
        this.expenseService = expenseService;
    }

    @GetMapping("/friends")
    public ResponseEntity<List<Friend>> getListOfFriend() {
        return new ResponseEntity<>(this.friendService.getListOfFriend(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/friend/{id}")
    public ResponseEntity<Friend> getFriend(@PathVariable Long id) {
        List<Expense> expenses = this.expenseService.getExpensesByIdFriend(id);
        Friend friend = this.friendService.getFriend(id).get();
        friend.setExpenses(expenses);
        return new ResponseEntity<>(friend, HttpStatus.ACCEPTED);
    }
    @GetMapping("/friend/balance")
    public ResponseEntity<List<FriendAdapter>> getFriendBalance() {

        this.totalDue = 0.0f;
        List<FriendAdapter> friendsBalance = new ArrayList<>();
        List<Friend> friends = this.friendService.getListOfFriend();
        friends.forEach(friend -> friendsBalance.add(new FriendAdapter(friend.getId(), friend.getName())));
        List<Expense> expenses =  this.expenseService.getExpenses();
        expenses.forEach(expense -> this.totalDue += expense.getAmount());
        friendsBalance.forEach(friendAdapter -> {
            expenses.forEach(expense -> {
                if(expense.getFriend().getId().equals(friendAdapter.getId())) {
                    Float balance = friendAdapter.getBalance() + expense.getAmount();
                    friendAdapter.setBalance(balance);
                }
            });
            friendAdapter.setBalance(friendAdapter.getBalance()-(this.totalDue / friendsBalance.size()));
        });
        return new ResponseEntity<>(friendsBalance, HttpStatus.OK);
    }

    @PostMapping("/friend")
    public void addFriend (@RequestBody Friend friend) {
        this.friendService.addFriend(friend);
    }

    @PostMapping("/expense/friend")
    public void setFriendHasPay(@RequestBody Friend friend) {this.friendService.addFriend(friend);}
}

