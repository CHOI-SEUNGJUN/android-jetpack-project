package c.june.learning.di

import android.content.Context
import c.june.learning.data.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    fun provideAppDatabase(context: Context): AppDatabase
        = AppDatabase.getInstance(context)

    fun provideTodoDao(db: AppDatabase) = db.getTodoDao()

    single { provideAppDatabase(androidContext()) }
    single { provideTodoDao(get()) }
}