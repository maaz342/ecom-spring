package com.maaz.Maaz.Ecommerce.service.interf;

import com.maaz.Maaz.Ecommerce.dto.LoginRequest;
import com.maaz.Maaz.Ecommerce.dto.Response;
import com.maaz.Maaz.Ecommerce.dto.UserDto;
import com.maaz.Maaz.Ecommerce.entity.User;

public interface UserService {
    Response registerUser(UserDto userdto);
    Response loginUser(LoginRequest loginRequest);;
Response getAllUsers();
User getLoginUser();
Response getUserInfoAndOrderHistory();
}
