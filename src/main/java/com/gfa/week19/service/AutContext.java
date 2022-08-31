package com.gfa.week19.service;

import com.gfa.week19.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Objects;

@Setter
@Getter
@Component @SessionScope
public class AutContext {

    User user;

    public boolean isLoggedIn() {
        return Objects.nonNull(user);
    }
}