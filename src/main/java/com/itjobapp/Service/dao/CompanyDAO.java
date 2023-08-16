package com.itjobapp.Service.dao;

import com.itjobapp.Database.entity.CompanyEntity;
import com.itjobapp.Service.domain.Company;
import com.itjobapp.Service.domain.JobOffer;

import java.util.List;
import java.util.Optional;

public interface CompanyDAO {
    Company create(Company company);

    List<CompanyEntity> getAllCompanies();


    Optional<Company> findByCompanyName(String companyName);

    Optional<Company> findById(Integer companyId);


    Optional<Company> findByEmail(String email);
}
