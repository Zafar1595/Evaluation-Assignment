package uz.domain.repository

import uz.domain.Result
import uz.domain.data.dao.LeadsDao
import uz.domain.entity.LeadEntity
import uz.domain.models.mapper.toDataModel
import uz.domain.models.mapper.toEntity

class LeadRepositoryImpl(private val leadsDao: LeadsDao) : ILeadRepository {
    override suspend fun getLeadsList(): Result<List<LeadEntity>> {
        return try {
            val response = leadsDao.getLeadsList()
            Result.Success(response.map { it.toEntity() })
        } catch (e: Exception) {
            Result.Error(e.message)
        }
    }

    override suspend fun getLeadById(id: Int): Result<LeadEntity> {
        return try {
            val response = leadsDao.getLeadById(id)
            Result.Success(response.toEntity())
        } catch (e: Exception) {
            Result.Error(e.message)
        }
    }

    override suspend fun addLead(leadEntity: LeadEntity): Result<LeadEntity> {
        return try {
            leadsDao.addLead(leadEntity.toDataModel())
            Result.Success(leadEntity)
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

    override suspend fun addLeads(leadsEntity: List<LeadEntity>) {
        leadsDao.addLeads(leadsEntity.map { it.toDataModel() })
    }
}