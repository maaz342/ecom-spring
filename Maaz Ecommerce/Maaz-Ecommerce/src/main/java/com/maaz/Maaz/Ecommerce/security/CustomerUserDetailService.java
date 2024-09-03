package com.maaz.Maaz.Ecommerce.security;

import com.maaz.Maaz.Ecommerce.entity.User;
import com.maaz.Maaz.Ecommerce.exception.NotFoundException;
import com.maaz.Maaz.Ecommerce.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerUserDetailService implements UserDetailsService {
    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepo.findByEmail(username).orElseThrow(()->new NotFoundException("User/Email Not Found"));
        return AuthUser.builder().user(user).build();
    }
}
