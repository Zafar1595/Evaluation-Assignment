package uz.domain.repository

import uz.domain.Result
import uz.domain.data.dao.LeadsDao
import uz.domain.entity.LeadEntity
import uz.domain.models.mapper.toDataModel
import uz.domain.models.mapper.toEntity

class LeadRepositoryImpl(private val leadsDao: LeadsDao) : ILeadRepository {
    override suspend fun getLeadsList(): uz.domain.Result<LeadEntity> {
        return Result.Success()
//        when (val response = db.getLeadsList()) {
//            is Result.Success -> Result.Success(response.data?.toEntity())
//            is Result.Error -> Result.Error(response.errorMessage)
//        }
    }

    override suspend fun getLeadById(id: Int): uz.domain.Result<LeadEntity> {
        return Result.Success()
//        when (val response = db.getLeadById(id)) {
//            is Result.Success -> Result.Success(response.data?.toEntity())
//            is Result.Error -> Result.Error(response.errorMessage)
//        }
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