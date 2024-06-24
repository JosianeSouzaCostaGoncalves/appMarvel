package com.example.appmarvel.view.di

import com.example.appmarvel.view.model.mapper.CharacterViewMapper
import com.example.appmarvel.view.ui.viewModel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val uiModules = module {
    factory { CharacterViewMapper() }
}

val viewModel = module {
    viewModel { MainViewModel(get(), get(),get(),get(),get(), get()) }
}

val viewModules = listOf<Module>(viewModel, uiModules)