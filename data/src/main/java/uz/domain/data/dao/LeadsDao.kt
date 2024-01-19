package uz.domain.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.domain.models.Intention
import uz.domain.models.Lead

@Dao
interface LeadsDao {

    @Query("SELECT * FROM leads_list")
    suspend fun getLeadsList(): List<Lead>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLead(lead: Lead)

    @Query("SELECT * FROM leads_list WHERE id = :id")
    suspend fun getLeadById(id: Int): Lead

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLeads(leads: List<Lead>)

}