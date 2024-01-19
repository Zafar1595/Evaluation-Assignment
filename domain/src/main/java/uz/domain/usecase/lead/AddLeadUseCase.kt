package uz.domain.usecase.lead

import uz.domain.entity.LeadEntity
import uz.domain.repository.ILeadRepository

class AddLeadUseCase(private val repository: ILeadRepository) {

    suspend operator fun invoke(leadEntity: LeadEntity) = repository.addLead(leadEntity = leadEntity)

}