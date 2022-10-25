package com.hh3.splitAu.service;

import com.hh3.splitAu.model.Friend;
import com.hh3.splitAu.repository.FriendRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class FriendService {

    private final FriendRepository friendRepository;

    public FriendService(FriendRepository friendRepository) {
        this.friendRepository = friendRepository;
    }

    public void addFriend(Friend friend) {
        this.friendRepository.save(friend);
    }

    public Optional<Friend> getFriend(Long id) {
        return this.friendRepository.findById(id);
    }

    public List<Friend> getListOfFriend() {return this.friendRepository.findAll();}
}
