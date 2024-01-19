package uz.domain.usecase.intention

import uz.domain.repository.IIntentionRepository

class GetAllIntentionsUseCase(private val repository: IIntentionRepository) {

    suspend operator fun invoke() = repository.getIntentions()

}