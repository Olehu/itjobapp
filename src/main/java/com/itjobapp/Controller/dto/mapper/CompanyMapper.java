package com.itjobapp.Controller.dto.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyMapper map(final CompanyMapper company)

}
