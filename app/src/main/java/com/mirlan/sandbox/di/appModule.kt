package com.mirlan.sandbox.di

import com.mirlan.sandbox.BuildConfig
import com.mirlan.sandbox.domain.repo.SalonsRepository
import com.mirlan.sandbox.domain.repo.PhotoRepository
import com.mirlan.sandbox.presentation.album.AlbumViewModel
import com.mirlan.sandbox.presentation.photos.PhotosViewModel
import com.google.gson.GsonBuilder
import com.mirlan.sandbox.data.service.Api
import com.mirlan.sandbox.domain.datasource.album.AlbumRemoteDataSource
import com.mirlan.sandbox.domain.datasource.photos.PhotoRemoteDataSource
import com.mirlan.sandbox.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val appModule = module {
    single { GsonBuilder().create() }
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }

    single { get<Retrofit>().create<Api>() }
    single { SalonsRepository(get()) }
    single { PhotoRepository(get()) }
    single {
        AlbumRemoteDataSource(
            get()
        )
    }
    single { PhotoRemoteDataSource(
            get()
        )
    }
    viewModel { PhotosViewModel(get()) }
    viewModel { AlbumViewModel(get()) }

}

