package uz.domain.repository

import uz.domain.Result
import uz.domain.data.dao.IntentionDao
import uz.domain.data.dao.LeadsDao
import uz.domain.entity.IntentionEntity
import uz.domain.models.mapper.toDataModel
import uz.domain.models.mapper.toEntity

class IntentionRepositoryImpl(private val dao: IntentionDao) : IIntentionRepository {
    override suspend fun getIntentionById(id: Int): Result<IntentionEntity> {
        return try {
            Result.Success(dao.getIntentionById(id).toEntity())
        } catch (e: Exception) {
            Result.Error(e.message ?: "Error getIntentionById")
        }
    }

    override suspend fun addIntentions(intentionsEntity: List<IntentionEntity>) {
        dao.addIntentionList(intentionsEntity.map { it.toDataModel() })
    }

    override suspend fun getIntentions(): Result<List<IntentionEntity>> {
        return try {
            Result.Success(dao.getIntentions().map { it.toEntity() })
        } catch (e: Exception) {
            Result.Error(e.message ?: "Error getIntentions")
        }
    }
}