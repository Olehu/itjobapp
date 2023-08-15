package com.itjobapp.Database.repository.mapper;


import com.itjobapp.Database.entity.CandidateEntity;
import com.itjobapp.Database.entity.CompanyEntity;
import com.itjobapp.Service.domain.Candidate;
import com.itjobapp.Service.domain.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompanyEntityMapper {

    CompanyEntity mapToEntity(Company company);

    @Mapping(target = "jobOffers", ignore = true)
    Company mapFromEntity(CompanyEntity companyEntity);

    List<Company> mapFromEntities(List<CompanyEntity> filteredEntities);
}
