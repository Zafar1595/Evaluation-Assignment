package uz.domain.usecase.country

import uz.domain.entity.CountryEntity
import uz.domain.entity.IntentionEntity
import uz.domain.repository.ICountryRepository
import uz.domain.repository.IIntentionRepository

class GetCountryByIdUseCase(private val repository: ICountryRepository) {

    suspend operator fun invoke(id: Int) = repository.getCountryById(id = id)

}