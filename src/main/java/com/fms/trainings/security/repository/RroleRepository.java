package com.fms.trainings.security.repository;

import com.fms.trainings.security.entities.Rrole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RroleRepository extends JpaRepository<Rrole, Long> {
    public Rrole findByRole(String roleName);
}
