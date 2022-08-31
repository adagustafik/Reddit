package com.gfa.week19.service;

import com.gfa.week19.model.User;

public interface UserService {

    boolean loginUser(User user);
    void logoutUser();
    boolean isLoggedIn();
    User getLoggedInUser();
}