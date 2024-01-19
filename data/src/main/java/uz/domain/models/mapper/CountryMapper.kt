package uz.domain.models.mapper

import uz.domain.models.Country

fun Country.toEntity() = uz.domain.entity.CountryEntity(
    id = id,
    name = name,
    code = code,
    flag = flag
)

fun uz.domain.entity.CountryEntity.toDataModel() = Country(
    id = id,
    name = name,
    code = code,
    flag = flag
)