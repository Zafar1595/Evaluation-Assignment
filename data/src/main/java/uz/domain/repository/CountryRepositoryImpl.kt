package uz.domain.repository

import uz.domain.Result
import uz.domain.data.dao.CountryDao
import uz.domain.data.dao.IntentionDao
import uz.domain.data.dao.LeadsDao
import uz.domain.entity.CountryEntity
import uz.domain.entity.IntentionEntity
import uz.domain.models.mapper.toDataModel
import uz.domain.models.mapper.toEntity

class CountryRepositoryImpl(private val dao: CountryDao) : ICountryRepository {
    override suspend fun getCountryById(id: Int): Result<CountryEntity> {
        return try {
            Result.Success(dao.getCountryById(id).toEntity())
        } catch (e: Exception) {
            Result.Error(e.message)
        }
    }

    override suspend fun addCountrys(countrysEntity: List<CountryEntity>) {
        dao.addCountrysList(countrys = countrysEntity.map { it.toDataModel() })
    }


}