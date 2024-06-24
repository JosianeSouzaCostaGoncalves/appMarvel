package com.example.appmarvel.data.di

import com.example.appmarvel.data.local.database.AppMarvelDataBase
import com.example.appmarvel.data.model.mapper.CharacterDataMapper
import com.example.appmarvel.data.model.repository.CharacterRepositoryImpl
import com.example.appmarvel.data.remote.retrofit.MyRetrofit
import com.example.appmarvel.data.remote.service.ServiceApi
import com.example.appmarvel.domain.model.repository.CharacterRepository

import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

val dbModule = module {
    single { AppMarvelDataBase.getInstance(androidContext()) }
    single { get<AppMarvelDataBase>().getMarvelDao() }
    single { get<AppMarvelDataBase>().getFavoriteDao() }
}
val apiModule = module {
    single<OkHttpClient> { MyRetrofit().getOkHttpClient(androidContext()) }

    single<String> { MyRetrofit().generatorMd5Hash(get()) }

    single<Retrofit> { MyRetrofit().getRetrofit(get()) }

    single<ServiceApi> { MyRetrofit().getApiService(get()) }
}

val modules = module {
    factory { CharacterDataMapper() }
    factory<CharacterRepository> { CharacterRepositoryImpl(get(), get(), get(), get(),get()) }
}

val dataModules = listOf<Module>( apiModule, modules, dbModule )