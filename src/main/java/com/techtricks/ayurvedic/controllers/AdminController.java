package com.techtricks.ayurvedic.controllers;
import com.techtricks.ayurvedic.exceptions.AdminAlreadyExistsExceptions;
import com.techtricks.ayurvedic.models.Admin;
import com.techtricks.ayurvedic.services.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;


    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    @PostMapping("/signup")
    public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin) throws AdminAlreadyExistsExceptions {
        Admin registeredAdmin = adminService.saveAdmin(admin);
        return ResponseEntity.ok(registeredAdmin);
    }

    
}
