package com.itjobapp.Controller.api;

import com.itjobapp.Controller.dto.mapper.CompanyMapper;
import com.itjobapp.Controller.dto.mapper.JobOfferMapper;
import com.itjobapp.Security.UserService;
import com.itjobapp.Service.CompanyService;
import com.itjobapp.Service.JobOfferService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CompanyDashboardApiController.class})
@ExtendWith(SpringExtension.class)
class CompanyDashboardApiControllerTest {
    @Autowired
    private CompanyDashboardApiController companyDashboardApiController;

    @MockBean
    private CompanyMapper companyMapper;

    @MockBean
    private CompanyService companyService;

    @MockBean
    private JobOfferMapper jobOfferMapper;

    @MockBean
    private JobOfferService jobOfferService;

    @MockBean
    private UserService userService;

@Test
    public void testGetCompanies() throws Exception {

    }
}

