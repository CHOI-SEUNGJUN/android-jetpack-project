package c.june.learning.di

import c.june.learning.data.source.remote.GithubService
import c.june.learning.data.repository.GithubRepository
import c.june.learning.data.repository.TodoRepository
import c.june.learning.data.source.local.TodoDao
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