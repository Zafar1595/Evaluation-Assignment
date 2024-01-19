package uz.domain.repository

import uz.domain.Result
import uz.domain.data.dao.CountryDao
import uz.domain.data.dao.IntentionDao
import uz.domain.data.dao.LanguageDao
import uz.domain.data.dao.LeadsDao
import uz.domain.entity.CountryEntity
import uz.domain.entity.IntentionEntity
import uz.domain.entity.LanguageEntity
import uz.domain.models.mapper.toDataModel
import uz.domain.models.mapper.toEntity

class LanguageRepositoryImpl(private val dao: LanguageDao) : ILanguageRepository {
    override suspend fun getLanguageById(id: Int): Result<LanguageEntity> {
        return try {
            Result.Success(dao.getLanguageById(languageId = id).toEntity())
        } catch (e: Exception) {
            Result.Error(e.message)
        }
    }

    override suspend fun addLanguages(languageEntity: List<LanguageEntity>) {
        dao.addLanguagesList(languages = languageEntity.map { it.toDataModel() })
    }
}