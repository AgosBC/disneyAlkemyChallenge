package ar.com.alkemy.disney.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.alkemy.disney.entities.Admin;
import ar.com.alkemy.disney.repos.AdminRepository;
@Service
public class AdminService {

    @Autowired
    private AdminRepository repo;

    public void crearCrearAdmin(Admin admin) {
        repo.save(admin);
    }
    
}
