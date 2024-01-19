package uz.domain.repository

import uz.domain.Result
import uz.domain.entity.LeadEntity

interface ILeadRepository {

    suspend fun getLeadsList(): Result<LeadEntity>

    suspend fun getLeadById(id: Int): Result<LeadEntity>

    suspend fun addLead(leadEntity: LeadEntity): Result<LeadEntity>

    suspend fun addLeads(leadsEntity: List<LeadEntity>)

}