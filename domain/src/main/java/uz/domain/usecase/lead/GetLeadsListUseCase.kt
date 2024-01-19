package uz.domain.usecase.lead

import uz.domain.repository.ILeadRepository

class GetLeadsListUseCase(private val repository: ILeadRepository) {

    suspend operator fun invoke() = repository.getLeadsList()

}