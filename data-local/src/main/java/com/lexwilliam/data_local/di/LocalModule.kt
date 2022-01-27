package com.lexwilliam.data_local.di

import com.lexwilliam.data.HistoryLocalSource
import com.lexwilliam.data.MyAnimeLocalSource
import com.lexwilliam.data_local.dao.HistoryDao
import com.lexwilliam.data_local.dao.MyAnimeDao
import com.lexwilliam.data_local.data.HistoryLocalSourceImpl
import com.lexwilliam.data_local.data.MyAnimeLocalSourceImpl
import com.lexwilliam.data_local.mapper.HistoryMapper
import com.lexwilliam.data_local.mapper.HistoryMapperImpl
import com.lexwilliam.data_local.mapper.MyAnimeMapper
import com.lexwilliam.data_local.mapper.MyAnimeMapperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideHistoryLocalSource(
        historyDao: HistoryDao,
        historyMapper: HistoryMapper
    ): HistoryLocalSource =
        HistoryLocalSourceImpl(historyDao, historyMapper)

    @Singleton
    @Provides
    fun provideMyAnimeLocalSource(
        myAnimeDao: MyAnimeDao,
        myAnimeMapper: MyAnimeMapper
    ): MyAnimeLocalSource =
        MyAnimeLocalSourceImpl(myAnimeDao, myAnimeMapper)

    @Singleton
    @Provides
    fun provideMyAnimeMapper(): MyAnimeMapper = MyAnimeMapperImpl()

    @Singleton
    @Provides
    fun provideHistoryMapper(): HistoryMapper = HistoryMapperImpl()
}