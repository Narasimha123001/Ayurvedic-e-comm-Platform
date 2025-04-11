package com.techtricks.ayurvedic.services;


import com.techtricks.ayurvedic.exceptions.AdminAlreadyExistsExceptions;
import com.techtricks.ayurvedic.models.Admin;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AdminService {

    public Optional<Admin> authenticate(String email, String password);


    public Admin saveAdmin(Admin admin) throws AdminAlreadyExistsExceptions;
}
