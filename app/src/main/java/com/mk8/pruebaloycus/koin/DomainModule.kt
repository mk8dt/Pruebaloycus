package com.mk8.pruebaloycus.koin

import com.mk8.domain.cache.EventListCache
import com.mk8.domain.provider.EventProvider
import com.mk8.domain.repository.EventRepository
import org.koin.dsl.module

val domainModule = module {

    single { EventProvider(get(), get()) }
    single { EventRepository(get()) }
    single { EventListCache() }
}