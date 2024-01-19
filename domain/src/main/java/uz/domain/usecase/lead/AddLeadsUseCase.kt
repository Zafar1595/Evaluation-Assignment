package uz.domain.usecase.lead

import uz.domain.entity.LeadEntity
import uz.domain.repository.ILeadRepository

class AddLeadsUseCase(private val repository: ILeadRepository) {

    suspend operator fun invoke(leadsEntity: List<LeadEntity>) = repository.addLeads(leadsEntity = leadsEntity)

}