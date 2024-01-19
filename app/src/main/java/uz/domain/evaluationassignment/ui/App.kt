package uz.domain.evaluationassignment.ui

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import uz.domain.evaluationassignment.di.appModule
import uz.domain.evaluationassignment.di.dataModule
import uz.domain.evaluationassignment.di.domainModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val modules = listOf(appModule, domainModule, dataModule)
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(modules)
        }
    }
}