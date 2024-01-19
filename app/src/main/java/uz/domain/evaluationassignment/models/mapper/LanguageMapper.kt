package uz.domain.evaluationassignment.models.mapper

import uz.domain.evaluationassignment.models.Language

fun Language.toEntity() = uz.domain.entity.LanguageEntity(
    id = id,
    name = name
)

fun uz.domain.entity.LanguageEntity.toAppModel() = Language(
    id = id,
    name = name
)