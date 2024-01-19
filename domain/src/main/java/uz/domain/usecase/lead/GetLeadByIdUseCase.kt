package uz.domain.usecase.lead

import uz.domain.repository.ILeadRepository

class GetLeadByIdUseCase(private val repository: ILeadRepository) {

    suspend operator fun invoke(id: Int) = repository.getLeadById(id)

}