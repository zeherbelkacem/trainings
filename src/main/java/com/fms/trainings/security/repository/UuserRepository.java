package com.fms.trainings.security.repository;

import com.fms.trainings.security.entities.Uuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UuserRepository extends JpaRepository<Uuser, Long> {
    public Uuser findByUserName(String username);
}
