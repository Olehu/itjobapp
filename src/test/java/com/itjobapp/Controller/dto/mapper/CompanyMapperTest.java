package com.itjobapp.Controller.dto.mapper;

import com.itjobapp.Controller.dto.CompanyDTO;
import com.itjobapp.Service.domain.Company;
import com.itjobapp.Util.Entities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompanyMapperTest {

    @InjectMocks
    private CompanyMapper companyMapper = Mappers.getMapper(CompanyMapper.class);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testMap() {
        Company company = Entities.getCompanyABC();

        CompanyDTO companyDTO = companyMapper.map(company);

        assertEquals(company.getCompanyName(), companyDTO.getCompanyName());
        assertEquals(company.getIndustry(), companyDTO.getIndustry());
        assertEquals(company.getCity(), companyDTO.getCity());
        assertEquals(company.getEmail(), companyDTO.getEmail());
        assertEquals(company.getIsHiring(), companyDTO.getIsHiring());
        assertEquals(company.getDescription(), companyDTO.getDescription());
        assertEquals(company.getJobOffers(), companyDTO.getJobOffers());
    }

    @Test
    public void testMaper() {
        CompanyDTO companyDTO = Entities.getCompanyDTOABC();

        Company company = companyMapper.maper(companyDTO);

        assertEquals(companyDTO.getCompanyName(), company.getCompanyName());
        assertEquals(companyDTO.getIndustry(), company.getIndustry());
        assertEquals(companyDTO.getCity(), company.getCity());
        assertEquals(companyDTO.getEmail(), company.getEmail());
        assertEquals(companyDTO.getIsHiring(), company.getIsHiring());
        assertEquals(companyDTO.getDescription(), company.getDescription());
        assertEquals(companyDTO.getJobOffers(), company.getJobOffers());
    }
}
