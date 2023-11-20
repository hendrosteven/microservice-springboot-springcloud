package com.kelaskoding.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kelaskoding.entity.UserInfo;
import com.kelaskoding.repository.UserInfoRepo;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepo userInfoRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepo.save(userInfo);
        return "User added successfully";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = userInfoRepo.findByName(username);
        return userInfo.map(UserInfoDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
