package uz.domain.usecase.language

import uz.domain.entity.CountryEntity
import uz.domain.entity.IntentionEntity
import uz.domain.repository.ICountryRepository
import uz.domain.repository.IIntentionRepository
import uz.domain.repository.ILanguageRepository

class GetLanguageByIdUseCase(private val repository: ILanguageRepository) {

    suspend operator fun invoke(id: Int) = repository.getLanguageById(id = id)

}