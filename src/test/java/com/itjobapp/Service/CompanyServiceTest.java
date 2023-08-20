package com.itjobapp.Service;

import com.itjobapp.Database.repository.mapper.CompanyEntityMapper;
import com.itjobapp.Database.repository.mapper.JobOfferEntityMapper;
import com.itjobapp.Service.dao.CompanyDAO;
import com.itjobapp.Service.domain.Company;
import com.itjobapp.Service.domain.JobOffer;
import com.itjobapp.Util.Entities;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    @Mock
    private CompanyDAO companyDAO;

    @Mock
    private CompanyEntityMapper companyEntityMapper;

    @Mock
    private JobOfferEntityMapper jobOfferEntityMapper;

    @InjectMocks
    private CompanyService companyService;

    @Test
    @DisplayName("Should find company by Name")
    void getCompanyByName() {
        Company company = Entities.getCompanyABC();

        when(companyDAO.findByCompanyName("ABC Company")).thenReturn(Optional.of(company));

        Company result = companyService.getCompanyByName("ABC Company");
        assertEquals(company, result);
        verify(companyDAO).findByCompanyName("ABC Company");

    }



    @Test
    @DisplayName("Should create company ")
    void shouldCreateCompany() {
        Company company = Entities.getCompanyABC();


        when(companyDAO.create(company)).thenReturn(company);


        Company result = companyService.createCompany(company);
        assertEquals(company, result);
        verify(companyDAO).create(company);
    }

    @Test
    @DisplayName("Should get list of All Company")
    void getAllCompanies() {
        List<Company> company = List.of(Entities.getCompanyABC(), Entities.getCompanyXYZ());

        when(companyDAO.getAllCompanies()).thenReturn(company);

        List<Company> result = companyService.getAllCompanies();

        assertEquals(company, result);
        verify(companyDAO).getAllCompanies();
    }

    @Test
    @DisplayName("Should search Company Company")
    void searchCompanies() {

        JobOffer jobOffer = Entities.jobOfferJunior();
        List<Company> company = List.of(Entities.getCompanyABC().withJobOffers(Set.of(jobOffer)), Entities.getCompanyXYZ());

        when(companyDAO.getAllCompanies()).thenReturn(company);

        List<Company> result = companyService.searchCompanies("New York", true, true );


        assertEquals(1, result.size());
        assertEquals(Entities.getCompanyABC().withJobOffers(Set.of(jobOffer)), result.get(0));
        verify(companyDAO).getAllCompanies();
    }

    @Test
    @DisplayName("Find Company by Email")
    void findCompanyByMail() {
        Company company = Entities.getCompanyABC();

        when(companyDAO.findByEmail("abc@abc.com")).thenReturn(Optional.of(company));

        Company result = companyService.getCompanyByEmail("abc@abc.com");
        assertEquals(company, result);
        verify(companyDAO).findByEmail("abc@abc.com");
    }

    @Test
    @DisplayName("update Company")
    void updateCompany() {
        Company company = Entities.getCompanyABC();

        when(companyDAO.update(company)).thenReturn(company.withCompanyName("New Name"));

        Company result = companyService.update(company);
        assertEquals(company.withCompanyName("New Name"), result);
        verify(companyDAO).update(company);
    }

    @Test
    @DisplayName("create Company by Mail")
    void createCompanyByMaile() {
        Company company = Entities.getCompanyABC();

        when(companyDAO.createByMail("abc@abc.com")).thenReturn(company);

        Company result = companyService.createCompanyByMail("abc@abc.com");
        assertEquals(company, result);
        verify(companyDAO).createByMail("abc@abc.com");
    }

}