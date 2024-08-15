package com.example.spring.service;

import com.example.spring.Security.UserDetailsImpl;
import com.example.spring.dto.response.CurrentUser;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService {

    private UserDetailsImpl currentUser;

    public void setCurrentUser(UserDetailsImpl user) {
        this.currentUser = user;
    }

    public CurrentUser getCurrentUser() {
        if (currentUser != null) {
            return new CurrentUser(
                    currentUser.getUsername()
            );
        }
        return null; // or throw an exception if preferred
    }

    public void clear() {
        this.currentUser = null;
    }
}

