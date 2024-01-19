package uz.domain.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.domain.data.dao.CountryDao
import uz.domain.data.dao.IntentionDao
import uz.domain.data.dao.LanguageDao
import uz.domain.data.dao.LeadsDao
import uz.domain.models.Country
import uz.domain.models.Language

import uz.domain.models.Lead
import uz.domain.models.Intention

@Database(version = 1, entities = [Lead::class, Country::class, Language::class, Intention::class])
abstract class LeadsDatabase : RoomDatabase() {
    abstract fun leadsDao(): LeadsDao

    abstract fun intentionDao(): IntentionDao

    abstract fun countryDao(): CountryDao

    abstract fun languageDao(): LanguageDao

}