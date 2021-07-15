package c.june.learning.di

import c.june.learning.data.GithubRepository
import c.june.learning.data.MainRepository
import c.june.learning.data.TodoRepository
import c.june.learning.data.dataStore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single { TodoRepository(get()) }
    single { GithubRepository(get()) }
    single { MainRepository(androidContext().dataStore) }
}