package ar.com.alkemy.disney.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.alkemy.disney.entities.Admin;
@Repository
public interface AdminRepository extends JpaRepository <Admin, Integer> {
    
}
