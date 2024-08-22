package com.zerobase.restdocspractice.service;

import com.zerobase.restdocspractice.model.User;
import com.zerobase.restdocspractice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User create(User user) {
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public User read(long id) {
        System.out.println("#############");
        System.out.println(id);
        return userRepository.findById(id).orElse(null);
    }

    public User update(User requestUser) {
        Optional<User> optionalUser = userRepository.findById(requestUser.getId());
        return optionalUser.map(user -> {
            user.setAccount(requestUser.getAccount());
            user.setEmail(requestUser.getEmail());
            user.setPhoneNumber(requestUser.getPhoneNumber());
            user.setUpdatedAt(LocalDateTime.now());
            return user;
        })
                .map(userRepository::save)
                .orElse(null);
    }

    public boolean delete(long id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }
}
