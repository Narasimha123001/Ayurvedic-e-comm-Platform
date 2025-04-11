package com.techtricks.ayurvedic.services;

import com.techtricks.ayurvedic.exceptions.AdminAlreadyExistsExceptions;
import com.techtricks.ayurvedic.models.Admin;
import com.techtricks.ayurvedic.repositories.AdminRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    private final PasswordEncoder passwordEncoder;

    public AdminServiceImpl(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Optional<Admin> authenticate(String email, String password) {

        Optional<Admin> optionalAdmin = adminRepository.findByEmail(email);
        if (optionalAdmin.isPresent() && passwordEncoder.matches(password, optionalAdmin.get().getPassword())) {
            return optionalAdmin;
        }
        return Optional.empty();
    }

    @Override
    public Admin saveAdmin(Admin admin) throws AdminAlreadyExistsExceptions {
        Optional<Admin> optionalAdmin = adminRepository.findByEmail(admin.getEmail());
        if (optionalAdmin.isPresent())
            throw new AdminAlreadyExistsExceptions("admin already exits on this email" + admin.getEmail() + "please try with another email");
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }
}