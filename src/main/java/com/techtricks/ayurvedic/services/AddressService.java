package com.techtricks.ayurvedic.services;

import com.techtricks.ayurvedic.dto.AddressDTO;
import com.techtricks.ayurvedic.exceptions.AddressNotFoundException;
import com.techtricks.ayurvedic.exceptions.UserNotFoundException;
import com.techtricks.ayurvedic.models.Address;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {

    public Address addAddress(Long userId, Address address) throws UserNotFoundException;

    public List<AddressDTO> getAddressesByUserId(Long userId);

    public Address updateAddress(Long addressId, Address updatedAddress) throws AddressNotFoundException;

    public void deleteAddress(Long addressId) throws AddressNotFoundException;
}
