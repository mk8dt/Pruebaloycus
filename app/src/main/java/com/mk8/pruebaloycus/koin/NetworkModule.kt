package com.mk8.pruebaloycus.koin

import com.mk8.domain.client.ApiClient
import com.mk8.pruebaloycus.network.ApiClientImpl
import com.mk8.pruebaloycus.network.EventService
import com.mk8.pruebaloycus.network.NetworkController
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {

    single<ApiClient> { ApiClientImpl(get(), get()) }

    single { InjectorModule.provideRetrofit() }

    single { get<Retrofit>().create(EventService::class.java) }

    single { NetworkController() }
}