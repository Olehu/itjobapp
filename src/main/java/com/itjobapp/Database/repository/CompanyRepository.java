package com.itjobapp.Database.repository;

import com.itjobapp.Database.entity.CompanyEntity;
import com.itjobapp.Database.repository.jpa.CompanyJpaRepository;
import com.itjobapp.Database.repository.mapper.CompanyEntityMapper;
import com.itjobapp.Service.dao.CompanyDAO;
import com.itjobapp.Service.domain.Company;
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
    @Override
    public Company create(Company company) {

        CompanyEntity toSave = companyEntityMapper.mapToEntity(company);
        CompanyEntity saved = companyJpaRepository.save(toSave);
        Company newCompany = companyEntityMapper.mapFromEntity(saved);
        return newCompany;
    }

    @Override
    public List<CompanyEntity> getAllCompanies() {
        return companyJpaRepository.findAll();
    }

    @Override
    public Optional<Company> findByCompanyName(String companyName) {
        Optional<CompanyEntity> companyEntity = companyJpaRepository.findByCompanyName(companyName);
        return companyEntity.map(companyEntityMapper::mapFromEntity);    }

    @Override
    public Optional<Company> findById(Integer companyId) {
        Optional<CompanyEntity> companyEntity = companyJpaRepository.findById(companyId);
        return companyEntity.map(companyEntityMapper::mapFromEntity);
    }

    @Override
    public List<CompanyEntity> searchCompanies(String location, Boolean isHiring, Boolean hasJobOffers) {
        return companyJpaRepository.searchCompanies(location, isHiring, hasJobOffers);
    }

    //    @Override
//    public List<CompanyEntity> searchCompanies(String location, Boolean isHiring, Boolean hasJobOffers) {
//        Specification<CompanyEntity> spec = Specification.where(null);
//
//        if (location != null && !location.isEmpty()) {
//            spec = spec.and((root, query, criteriaBuilder) ->
//                    criteriaBuilder.equal(root.get("location"), location));
//        }
//
//        if (isHiring != null) {
//            spec = spec.and((root, query, criteriaBuilder) ->
//                    criteriaBuilder.equal(root.get("isHiring"), isHiring));
//        }
//
//        if (hasJobOffers != null) {
//            if (hasJobOffers) {
//                spec = spec.and((root, query, criteriaBuilder) ->
//                        criteriaBuilder.isNotEmpty(root.get("jobOffers")));
//            } else {
//                spec = spec.and((root, query, criteriaBuilder) ->
//                        criteriaBuilder.isEmpty(root.get("jobOffers")));
//            }
//        }
//
//        List<CompanyEntity> filteredEntities = companyJpaRepository.searchCompanies(spec);
//        return filteredEntities;
//    }
}
