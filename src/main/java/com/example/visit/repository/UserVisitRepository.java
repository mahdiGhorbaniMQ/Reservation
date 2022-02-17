package com.example.visit.repository;

import com.example.visit.models.UserVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserVisitRepository extends JpaRepository<UserVisit,Long> {
    Collection<UserVisit> findAllByEmail(String email);
    UserVisit getByVisitId(Long visitId);
}
