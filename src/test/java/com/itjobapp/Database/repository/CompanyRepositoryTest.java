package com.itjobapp.Database.repository;

import com.itjobapp.Service.domain.Company;
import com.itjobapp.Util.Entities;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CompanyRepositoryTest {
    @Autowired
    private CompanyRepository companyRepository;

    @Test
    @Transactional
    public void testCreateCompanyAndFindByName() {
        Company company = Entities.getCompanyABC();
        Company companyCreated = companyRepository.create(company);
        Optional<Company> found = companyRepository.findByCompanyName(company.getCompanyName());

        assertTrue(found.isPresent());
        assertEquals(company, companyCreated);
        assertEquals(company, found.get());
    }

    @Test
    @Transactional
    public void testGetAllCompanies() {
        Company company = Entities.getCompanyABC();
        Company company2 = Entities.getCompanyXYZ();

        companyRepository.create(company);
        companyRepository.create(company2);

        List<Company> allCompanies = companyRepository.getAllCompanies();

        assertEquals(2, allCompanies.size());
    }

    @Test
    @Transactional
    public void testUpdateCompany() {
        Company company = Entities.getCompanyABC();
        Company companyCreated = companyRepository.create(company);

        Company updatedCompany = companyCreated.withCompanyName("New Company Name");
        Company updated = companyRepository.update(updatedCompany);

        assertEquals(updatedCompany, updated);
        assertNotEquals(company, updated);
    }

    @Test
    @Transactional
    public void testCreateByMail() {
        String email = "test@gmail.com";
        Company company = companyRepository.createByMail(email);

        assertEquals(email, company.getEmail());
    }

    @Test
    @Transactional
    public void findByMail() {
        Company company = Entities.getCompanyABC();
        companyRepository.create(company);

        Optional<Company> find = companyRepository.findByEmail(company.getEmail());

        assertEquals(company, find.get());
    }

}

