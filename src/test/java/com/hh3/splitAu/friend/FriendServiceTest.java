package com.hh3.splitAu.friend;

import com.hh3.splitAu.model.Friend;
import com.hh3.splitAu.repository.FriendRepository;
import com.hh3.splitAu.service.FriendService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class FriendServiceTest {

    @Mock
    private FriendRepository friendRepository;
    private FriendService friendService;

    @BeforeEach
    void setUp() {
        friendService = new FriendService(friendRepository);
    }

    @Test
    void addFriend() {
        Friend friend = new Friend();
        friend.setId(10L);
        friend.setName("test");

        friendService.addFriend(friend);

        ArgumentCaptor<Friend> friendArgumentCaptor = ArgumentCaptor.forClass(Friend.class);
        verify(friendRepository).save(friendArgumentCaptor.capture());
        Friend capturedFriend = friendArgumentCaptor.getValue();

        Assertions.assertEquals(capturedFriend,friend);
    }

    @Test
    void getFriend() {
        friendService.getFriend(1L);
        verify(friendRepository).findById(1L);
    }

    @Test
    void getListOfFriend() {
        friendService.getListOfFriend();
        verify(friendRepository).findAll();
    }

    @Test
    void getFriendBalance() {
        friendService.getListOfFriend();
        verify(friendRepository).findAll();
    }
}