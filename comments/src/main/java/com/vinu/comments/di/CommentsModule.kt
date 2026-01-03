package com.vinu.comments.di

import com.vinu.comments.data.api.CommentsApi
import com.vinu.comments.data.repository.CommentsMapper
import com.vinu.comments.data.repository.CommentsRepositoryImp
import com.vinu.comments.data.repository.CommentsService
import com.vinu.comments.domain.repository.CommentsRepository
import com.vinu.comments.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CommentsModule {

    @Singleton
    @Provides
    fun provideCommentsApi(retrofit: Retrofit): CommentsApi =
        retrofit.create(CommentsApi::class.java)

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideCommentsRepository(
        service: CommentsService,
        mapper: CommentsMapper
    ): CommentsRepository = CommentsRepositoryImp(service, mapper)

}