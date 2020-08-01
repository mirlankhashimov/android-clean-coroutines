package com.mirlan.sandbox.di

import com.mirlan.sandbox.BuildConfig
import com.mirlan.sandbox.domain.repo.SalonsRepository
import com.mirlan.sandbox.presentation.home.HomeViewModel
import com.google.gson.GsonBuilder
import com.mirlan.sandbox.data.impl.SalonsRepositoryImpl
import com.mirlan.sandbox.data.service.Api
import com.mirlan.sandbox.domain.interactor.GetSalonUseCase
import com.mirlan.sandbox.domain.interactor.GetSalonsUseCase
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
    factory {
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
    factory { GetSalonsUseCase(repository = get()) }
    factory { GetSalonUseCase(repository = get()) }
    single<SalonsRepository> { SalonsRepositoryImpl(api = get()) }
    viewModel { HomeViewModel(getSalonsUseCase = get(), getSalonUseCase = get()) }
}