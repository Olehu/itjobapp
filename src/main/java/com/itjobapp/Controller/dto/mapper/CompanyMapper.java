package com.itjobapp.Controller.dto.mapper;

import com.itjobapp.Controller.dto.CompanyDTO;
import com.itjobapp.Service.domain.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyDTO map(final Company company);
    Company maper(final CompanyDTO companydto);

}
