package uz.domain.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.domain.models.Country
import uz.domain.models.Intention
import uz.domain.models.Lead

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCountrysList(countrys: List<Country>)

    @Query("SELECT * FROM countries_list WHERE id = :countryId")
    suspend fun getCountryById(countryId: Int): Country

    @Query("SELECT * FROM countries_list")
    suspend fun getAllCountrys(): List<Country>
}