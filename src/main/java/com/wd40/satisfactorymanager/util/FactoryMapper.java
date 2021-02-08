package com.wd40.satisfactorymanager.util;

import com.wd40.satisfactorymanager.dto.FactoryDto;
import com.wd40.satisfactorymanager.model.Factory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FactoryMapper {

  FactoryMapper INSTANCE = Mappers.getMapper(FactoryMapper.class);

  FactoryDto toDto(Factory factory);
}
