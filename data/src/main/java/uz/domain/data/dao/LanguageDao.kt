package uz.domain.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.domain.models.Country
import uz.domain.models.Intention
import uz.domain.models.Language
import uz.domain.models.Lead

@Dao
interface LanguageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLanguagesList(languages: List<Language>)

    @Query("SELECT * FROM languages_list WHERE id = :languageId")
    suspend fun getLanguageById(languageId: Int): Language

}