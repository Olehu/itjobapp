package com.itjobapp.Database.repository;

import com.itjobapp.Database.entity.CandidateEntity;
import com.itjobapp.Database.entity.CompanyEntity;
import com.itjobapp.Database.entity.JobOfferEntity;
import com.itjobapp.Database.repository.jpa.CompanyJpaRepository;
import com.itjobapp.Database.repository.mapper.CompanyEntityMapper;
import com.itjobapp.Database.repository.mapper.JobOfferEntityMapper;
import com.itjobapp.Service.dao.CompanyDAO;
import com.itjobapp.Service.domain.Company;
import com.itjobapp.Service.domain.JobOffer;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CompanyRepository implements CompanyDAO {


    private final CompanyEntityMapper companyEntityMapper;
    private final CompanyJpaRepository companyJpaRepository;

    private final JobOfferEntityMapper  jobOfferEntityMapper;
    @Override
    public Company create(Company company) {

        CompanyEntity toSave = companyEntityMapper.mapToEntity(company);
        CompanyEntity saved = companyJpaRepository.save(toSave);
        Company newCompany = companyEntityMapper.mapFromEntity(saved);
        return newCompany;
    }

    @Override
    public List<Company> getAllCompanies() {

        return companyJpaRepository.findAll().stream().map(companyEntityMapper::mapFromEntity).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public Optional<Company> findByCompanyName(String companyName) {
        Optional<CompanyEntity> companyEntity = companyJpaRepository.findByCompanyName(companyName);
        return companyEntity.map(companyEntityMapper::mapFromEntity);    }


    @Override
    public Optional<Company> findByEmail(String email) {
        Optional<CompanyEntity> companyEntity = companyJpaRepository.findByEmail(email);
        return companyEntity.map(companyEntityMapper::mapFromEntity);
    }

    @Override
    public Company update(Company existingCompany) {
        CompanyEntity search = companyJpaRepository.findByEmail(existingCompany.getEmail()).orElseThrow(()
                -> new RuntimeException("Company not found"));

        CompanyEntity toSave = search.withCompanyName(existingCompany.getCompanyName())
                .withIndustry(existingCompany.getIndustry())
                .withCity(existingCompany.getCity())
                .withEmail(existingCompany.getEmail())
                .withIsHiring(existingCompany.getIsHiring())
                .withJobOffers(companyEntityMapper.mapToEntity(existingCompany).getJobOffers());

        CompanyEntity saved = companyJpaRepository.save(toSave);
        return companyEntityMapper.mapFromEntity(saved);
    }

    @Override
    public Company createByMail(String email) {
        CompanyEntity toSave = new CompanyEntity().withEmail(email);
        CompanyEntity saved = companyJpaRepository.save(toSave);
        return companyEntityMapper.mapFromEntity(saved);
    }


}
