package uz.domain.models.mapper

import uz.domain.models.Language

fun Language.toEntity() = uz.domain.entity.LanguageEntity(
    id = id,
    name = name
)

fun uz.domain.entity.LanguageEntity.toDataModel() = Language(
    id = id,
    name = name
)