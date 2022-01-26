package com.lexwilliam.data_local.di

import android.content.Context
import androidx.room.Room
import com.lexwilliam.data_local.HistoryDatabase
import com.lexwilliam.data_local.MyAnimeDatabase
import com.lexwilliam.data_local.dao.HistoryDao
import com.lexwilliam.data_local.dao.MyAnimeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideHistoryDb(@ApplicationContext context: Context): HistoryDatabase {
        return Room
            .databaseBuilder(
                context,
                HistoryDatabase::class.java,
                "history_database"
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideHistoryDAO(historyDatabase: HistoryDatabase): HistoryDao {
        return historyDatabase.historyDao()
    }

    @Provides
    @Singleton
    fun provideMyAnimeDb(@ApplicationContext context: Context): MyAnimeDatabase {
        return Room
            .databaseBuilder(
                context,
                MyAnimeDatabase::class.java,
                "myAnime_database"
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideMyAnimeDAO(myAnimeDatabase: MyAnimeDatabase): MyAnimeDao {
        return myAnimeDatabase.myAnimeDao()
    }

}