package org.food.delivery.service;

import org.food.delivery.entity.User;

import java.util.List;

public interface UserService {
    User createUserWithCard(User user);

    List<User> getAllUsersWithCardDetails();
}
