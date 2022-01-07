package ar.com.alkemy.disney.services;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.alkemy.disney.entities.Admin;
import ar.com.alkemy.disney.repos.AdminRepository;

public class AdminService {

    @Autowired
    AdminRepository repo;

    public void crearCrearAdmin(Admin admin) {
        repo.save(admin);
    }
    
}
