package com.itjobapp.Database.repository.jpa;

import com.itjobapp.Database.entity.CompanyEntity;
import com.itjobapp.Database.entity.JobOfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyJpaRepository extends JpaRepository<CompanyEntity, Integer>, JpaSpecificationExecutor<CompanyEntity> {
    Optional<CompanyEntity> findByCompanyName(String companyName);


    Optional<CompanyEntity> findByEmail(String email);
}
