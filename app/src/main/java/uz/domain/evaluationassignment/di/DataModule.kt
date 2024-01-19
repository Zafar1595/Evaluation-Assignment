package uz.domain.evaluationassignment.di

import android.util.Log
import androidx.room.Room
import org.koin.dsl.module
import uz.domain.data.dao.CountryDao
import uz.domain.data.dao.IntentionDao
import uz.domain.data.dao.LanguageDao
import uz.domain.data.dao.LeadsDao
import uz.domain.data.db.LeadsDatabase

val dataModule = module {

    single<LeadsDatabase> {
        Room.databaseBuilder(
            context = get(),
            klass = LeadsDatabase::class.java,
            name = "leads_database.db"
        ).createFromAsset("leads_database.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    factory<LeadsDao> {
        get<LeadsDatabase>().leadsDao()
    }

    factory<IntentionDao> {
        get<LeadsDatabase>().intentionDao()
    }

    factory<CountryDao> {
        get<LeadsDatabase>().countryDao()
    }

    factory<LanguageDao> {
        get<LeadsDatabase>().languageDao()
    }

}