package com.gfa.week19.service;

import com.gfa.week19.model.User;
import com.gfa.week19.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final AutContext autContext;
    private final UserRepository userRepository;

    @Override
    public boolean loginUser(User user) {
        User ourUser = userRepository.findByName(user.getName());
        if (Objects.isNull(ourUser)) {
            userRepository.save(user);
            autContext.setUser(user);
            return true;
        }
        if (user.getPassword().equals(ourUser.getPassword())) {
            autContext.setUser(ourUser);
            return true;
        }
        return false;
    }

    @Override
    public void logoutUser() {
        autContext.setUser(null);
    }

    @Override
    public boolean isLoggedIn() {
        return autContext.isLoggedIn();
    }

    @Override
    public User getLoggedInUser() {
        return autContext.getUser();
    }
}