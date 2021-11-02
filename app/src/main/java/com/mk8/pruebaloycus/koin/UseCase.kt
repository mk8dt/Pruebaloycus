package com.mk8.pruebaloycus.koin

import com.mk8.domain.usecase.GetEventByIdentifierUseCase
import com.mk8.domain.usecase.GetEventListUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory { GetEventListUseCase(get()) }

    factory { GetEventByIdentifierUseCase(get()) }
}