package com.maaz.Maaz.Ecommerce.service.impl;

import com.maaz.Maaz.Ecommerce.dto.LoginRequest;
import com.maaz.Maaz.Ecommerce.dto.Response;
import com.maaz.Maaz.Ecommerce.dto.UserDto;
import com.maaz.Maaz.Ecommerce.entity.User;
import com.maaz.Maaz.Ecommerce.enums.UserRole;
import com.maaz.Maaz.Ecommerce.exception.InvalidCredentialsException;
import com.maaz.Maaz.Ecommerce.exception.NotFoundException;
import com.maaz.Maaz.Ecommerce.mappers.EntityDtoMapper;
import com.maaz.Maaz.Ecommerce.repository.UserRepo;
import com.maaz.Maaz.Ecommerce.security.JwtUtils;
import com.maaz.Maaz.Ecommerce.service.interf.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final EntityDtoMapper entityDtoMapper;


    @Override
    public Response registerUser(UserDto userdto) {
        UserRole userRole = UserRole.USER;

        if (userdto.getRole() != null && userdto.getRole().equalsIgnoreCase("admin")) {
            userRole = UserRole.ADMIN;
        }
        User user = User.builder().name(userdto.getName()).email(userdto.getEmail()).password(passwordEncoder.encode(userdto.getPassword())).password(userdto.getPassword()).role(userRole).build();
        User savedUser = userRepo.save(user);
        return Response.builder().status(200).message("User Added").user(userdto).build();
    }

    @Override
    public Response loginUser(LoginRequest loginRequest) {
        User user=userRepo.findByEmail(loginRequest.getEmail()).orElseThrow(()->new NotFoundException("Email Not Found"));
      if(!passwordEncoder.matches(loginRequest.getPassword(),user.getPassword())){
          throw  new InvalidCredentialsException("Password Not Matched");
      }
      String token=jwtUtils.generateToken(user);
      return Response.builder().status(200).message("User Logged In").token(token).expirationTime("6 month").role(user.getRole().name()).build();
    }

    @Override
    public Response getAllUsers() {
        List<User> users=userRepo.findAll();
        List<UserDto> userDtos=users.stream().map(entityDtoMapper::mapUserToDtoBasic).toList();



        return Response.builder().status(200).message("User Fetched").userList(userDtos).build();
    }

    @Override
    public User getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String  email = authentication.getName();
        log.info("User Email is: " + email);
        return userRepo.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("User Not found"));
    }

    @Override
    public Response getUserInfoAndOrderHistory() {
        User user = getLoginUser();
        UserDto userDto = entityDtoMapper.mapUserToDtoPlusAddressAndOrderHistory(user);

        return Response.builder()
                .status(200)
                .user(userDto)
                .build();
    }
}
