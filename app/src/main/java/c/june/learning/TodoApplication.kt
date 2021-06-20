package c.june.learning

import android.app.Application
import c.june.learning.di.databaseModule
import c.june.learning.di.netModule
import c.june.learning.di.repositoryModule
import c.june.learning.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TodoApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TodoApplication)
            modules(modules)
        }
    }

    private companion object {
        val modules = listOf(
            databaseModule,
            repositoryModule,
            viewModelModule,
            netModule
        )
    }

}