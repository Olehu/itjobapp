package com.itjobapp.Service;

import com.itjobapp.Controller.dto.JobOfferDTO;
import com.itjobapp.Controller.web.exception.NotFoundException;
import com.itjobapp.Database.entity.CompanyEntity;
import com.itjobapp.Database.repository.mapper.CompanyEntityMapper;
import com.itjobapp.Database.repository.mapper.JobOfferEntityMapper;
import com.itjobapp.Service.dao.CompanyDAO;
import com.itjobapp.Service.domain.Company;
import com.itjobapp.Service.domain.JobOffer;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompanyService {

    private final CompanyDAO companyDao;
    private final CompanyEntityMapper companyEntityMapper;
    private final JobOfferEntityMapper  jobOfferEntityMapper;


    @Transactional
    public Company createCompany(Company company) {
        return companyDao.create(company);
    }

    public List<Company> getAllCompanies() {
        List<CompanyEntity> companyEntities = companyDao.getAllCompanies();
        return companyEntities.stream()
                .map(companyEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }



    public Company getCompanyByName(String companyName) {
        return companyDao.findByCompanyName(companyName)
                .orElseThrow(() -> new NotFoundException("Company not found"));
    }

    public Company getCompanyById(Integer companyId) {
        return companyDao.findById(companyId)
                .orElseThrow(() -> new NotFoundException("Company not found"));
    }

    public List<Company> searchCompanies(String location, Boolean isHiring, Boolean hasJobOffers) {
        List<CompanyEntity> companyEntities = companyDao.getAllCompanies();

        return companyEntities.stream()
                .filter(companyEntity -> {
                    boolean matches = true;

                    if (location != null && !location.isEmpty()) {
                        matches = companyEntity.getLocation().equalsIgnoreCase(location);
                    }

                    if (isHiring != null) {
                        matches = matches && companyEntity.getIsHiring().equals(isHiring);
                    }

                    if (hasJobOffers != null) {
                        boolean companyHasJobOffers = !companyEntity.getJobOffers().isEmpty();
                        matches = matches && (hasJobOffers.equals(companyHasJobOffers));
                    }

                    return matches;
                })
                .map(companyEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }


}
