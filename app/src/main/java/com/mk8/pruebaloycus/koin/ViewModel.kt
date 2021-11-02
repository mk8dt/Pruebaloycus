package com.mk8.pruebaloycus.koin

import com.mk8.pruebaloycus.screen.detail.DetailViewModel
import com.mk8.pruebaloycus.screen.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { HomeViewModel(get()) }

    viewModel { DetailViewModel(get()) }
}