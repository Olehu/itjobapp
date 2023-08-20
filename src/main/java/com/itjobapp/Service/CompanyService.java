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


    @Transactional
    public Company createCompany(Company company) {
        return companyDao.create(company);
    }

    public List<Company> getAllCompanies() {
        return companyDao.getAllCompanies();
    }



    public Company getCompanyByName(String companyName) {
        return companyDao.findByCompanyName(companyName)
                .orElseThrow(() -> new NotFoundException("Company not found"));
    }



    public List<Company> searchCompanies(String location, Boolean isHiring, Boolean hasJobOffers) {
        List<Company> company = companyDao.getAllCompanies();

        return company.stream()
                .filter(record -> {
                    boolean matches = true;

                    if (location != null && !location.isEmpty()) {
                        matches = record.getCity().equalsIgnoreCase(location);
                    }

                    if (isHiring != null) {
                        matches = matches && record.getIsHiring().equals(isHiring);
                    }

                    if (hasJobOffers != null) {
                        boolean recordHasJobOffers = !record.getJobOffers().isEmpty();
                        matches = matches && (hasJobOffers.equals(recordHasJobOffers));
                    }

                    return matches;
                })
                .collect(Collectors.toList());
    }


    public Company getCompanyByEmail(String email) {
        return companyDao.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Company not found"));
    }

    public Company update(Company existingCompany) {
        return companyDao.update(existingCompany);
    }

    public Company createCompanyByMail(String email) {
        return companyDao.createByMail(email);
    }
}


