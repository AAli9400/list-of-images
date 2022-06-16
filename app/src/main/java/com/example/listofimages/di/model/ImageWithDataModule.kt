package com.example.listofimages.di.model

import com.example.listofimages.data.api.constants.ApiConstants
import com.example.listofimages.data.api.services.ImagesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object ImageWithDataModule {
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(ApiConstants.Urls.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideImagesService(
        retrofit: Retrofit
    ): ImagesService = retrofit.create(ImagesService::class.java)
}