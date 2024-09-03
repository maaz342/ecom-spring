package com.maaz.Maaz.Ecommerce.service.interf;

import com.maaz.Maaz.Ecommerce.dto.AddressDto;
import com.maaz.Maaz.Ecommerce.dto.Response;

public interface AddressService {
    Response saveAndUpdateAddress(AddressDto addressDto);
}
