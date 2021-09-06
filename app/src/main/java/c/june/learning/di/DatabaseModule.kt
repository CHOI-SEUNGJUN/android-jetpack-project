package c.june.learning.di

import android.content.Context
import androidx.room.Room
import c.june.learning.data.AppDatabase
import c.june.learning.data.TodoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "LEARNING_DB"
        ).build()
    }

    @Singleton
    @Provides
    fun provideTodoDao(db: AppDatabase): TodoDao = db.getTodoDao()
}