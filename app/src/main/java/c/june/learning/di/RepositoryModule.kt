package c.june.learning.di

import c.june.learning.api.GithubService
import c.june.learning.data.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideTodoRepository(todoDao: TodoDao): TodoRepository {
        return TodoRepository(todoDao)
    }

    @Singleton
    @Provides
    fun provideGithubRepository(service: GithubService): GithubRepository {
        return GithubRepository(service)
    }
}