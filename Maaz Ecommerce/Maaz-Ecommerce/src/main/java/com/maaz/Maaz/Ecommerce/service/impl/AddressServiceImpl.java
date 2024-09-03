package com.maaz.Maaz.Ecommerce.service.impl;

import com.maaz.Maaz.Ecommerce.dto.AddressDto;
import com.maaz.Maaz.Ecommerce.dto.Response;
import com.maaz.Maaz.Ecommerce.entity.Address;
import com.maaz.Maaz.Ecommerce.entity.User;
import com.maaz.Maaz.Ecommerce.repository.AddressRepository;
import com.maaz.Maaz.Ecommerce.service.interf.AddressService;
import com.maaz.Maaz.Ecommerce.service.interf.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final UserService userService;

    @Override
    public Response saveAndUpdateAddress(AddressDto addressDto) {
User user= userService.getLoginUser();
Address address=user.getAddress();
if(address==null){
    address=new Address();
    address.setUser(user);
}
if(addressDto.getStreet()!=null)address.setStreet(addressDto.getStreet());
if(addressDto.getCity()!=null)address.setCity(addressDto.getCity());
if(addressDto.getState()!=null)address.setState(addressDto.getState());
if(addressDto.getZipCode()!=null)address.setZip(addressDto.getZipCode());
if(addressDto.getCountry()!=null)address.setCountry(addressDto.getCountry());
addressRepository.save(address);
String message=(user.getAddress()==null)?"Address Created Successfully":"Address Successfully Updated";
return Response.builder().status(200).message(message).build();




    }
}
