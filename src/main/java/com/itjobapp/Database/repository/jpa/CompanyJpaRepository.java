package com.itjobapp.Database.repository.jpa;

import com.itjobapp.Database.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyJpaRepository extends JpaRepository<CompanyEntity, Integer> {
    Optional<CompanyEntity> findByCompanyName(String companyName);

    Integer findIdByName(String companyName);
}
