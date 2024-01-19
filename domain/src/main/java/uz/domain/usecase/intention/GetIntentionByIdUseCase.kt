package uz.domain.usecase.intention

import uz.domain.repository.IIntentionRepository

class GetIntentionByIdUseCase(private val repository: IIntentionRepository) {

    suspend operator fun invoke(id: Int) = repository.getIntentionById(id = id)

}