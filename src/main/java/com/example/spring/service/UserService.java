package com.example.spring.service;

import com.example.spring.Security.UserDetailsImpl;
import com.example.spring.dto.response.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private CurrentUser currentUser;

    public void setCurrentUser(CurrentUser user) {
        this.currentUser = user;
    }

    public String getCurrentUser() {
        if (currentUser != null) {

                    currentUser.getUsername();
        }
        return null;
    }

    public void clear() {
        this.currentUser = null;
    }
}
