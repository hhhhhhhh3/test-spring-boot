package com.hh3.splitAu.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@AllArgsConstructor
@Getter
public class FriendAdapter {
    private Long id;
    private String name;
    private Float balance;

    public FriendAdapter(Long id, String name) {
        this.id = id;
        this.name = name;
        this.balance = 0.0F;
    }
}
