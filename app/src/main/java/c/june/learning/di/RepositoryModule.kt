package c.june.learning.di

import c.june.learning.data.TodoRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { TodoRepository(get()) }
}