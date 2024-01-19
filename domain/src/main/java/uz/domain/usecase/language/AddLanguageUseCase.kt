package uz.domain.usecase.language

import uz.domain.entity.IntentionEntity
import uz.domain.entity.LanguageEntity
import uz.domain.repository.IIntentionRepository
import uz.domain.repository.ILanguageRepository

class AddLanguageUseCase(private val repository: ILanguageRepository) {

    suspend operator fun invoke(languageEntity: List<LanguageEntity>) = repository.addLanguages(languageEntity = languageEntity)

}