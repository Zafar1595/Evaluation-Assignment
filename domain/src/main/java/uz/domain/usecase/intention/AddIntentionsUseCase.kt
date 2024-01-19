package uz.domain.usecase.intention

import uz.domain.entity.IntentionEntity
import uz.domain.repository.IIntentionRepository

class AddIntentionsUseCase(private val repository: IIntentionRepository) {

    suspend operator fun invoke(intentionsEntity: List<IntentionEntity>) = repository.addIntentions(intentionsEntity = intentionsEntity)

}