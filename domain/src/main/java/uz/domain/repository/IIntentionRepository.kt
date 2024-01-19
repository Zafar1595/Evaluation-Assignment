package uz.domain.repository

import uz.domain.Result
import uz.domain.entity.LeadEntity
import uz.domain.entity.IntentionEntity

interface IIntentionRepository {

    suspend fun getIntentionById(id: Int): Result<IntentionEntity>

    suspend fun addIntentions(intentionsEntity: List<IntentionEntity>)

}