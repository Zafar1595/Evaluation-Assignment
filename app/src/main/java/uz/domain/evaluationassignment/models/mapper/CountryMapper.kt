package uz.domain.evaluationassignment.models.mapper

import uz.domain.evaluationassignment.models.Country

fun Country.toEntity() = uz.domain.entity.CountryEntity(
    id = id,
    name = name,
    code = code,
    flag = flag
)

fun uz.domain.entity.CountryEntity.toAppModel() = Country(
    id = id,
    name = name,
    code = code,
    flag = flag
)