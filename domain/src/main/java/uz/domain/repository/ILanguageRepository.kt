package uz.domain.repository

import uz.domain.Result
import uz.domain.entity.CountryEntity
import uz.domain.entity.LeadEntity
import uz.domain.entity.IntentionEntity
import uz.domain.entity.LanguageEntity

interface ILanguageRepository {

    suspend fun getLanguageById(id: Int): Result<LanguageEntity>

    suspend fun addLanguages(languageEntity: List<LanguageEntity>)

}