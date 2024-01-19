package uz.domain.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.domain.models.Intention
import uz.domain.models.Lead

@Dao
interface IntentionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addIntentionList(intentionList: List<Intention>)

    @Query("SELECT * FROM intention_list WHERE id = :leadId")
    suspend fun getIntentionById(leadId: Int): Intention

}