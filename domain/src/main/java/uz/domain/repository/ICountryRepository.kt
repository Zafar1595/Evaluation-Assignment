package uz.domain.repository

import uz.domain.Result
import uz.domain.entity.CountryEntity
import uz.domain.entity.LeadEntity
import uz.domain.entity.IntentionEntity

interface ICountryRepository {

    suspend fun getCountryById(id: Int): Result<CountryEntity>

    suspend fun addCountrys(countrysEntity: List<CountryEntity>)

    suspend fun getAllCountrys(): Result<List<CountryEntity>>

}