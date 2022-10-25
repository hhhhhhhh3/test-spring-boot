package com.hh3.splitAu.model;

import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Data
@Setter
@Entity

@Table(name = "expense")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Float amount;
    private Date date;


    @ManyToOne
    @JoinColumn(name = "friend_id")
    private Friend friend;

}
