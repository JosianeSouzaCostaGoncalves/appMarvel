package com.example.appmarvel.domain.di

import com.example.appmarvel.data.model.repository.CharacterRepositoryImpl
import com.example.appmarvel.domain.model.repository.CharacterRepository
import com.example.appmarvel.domain.model.useCase.DeleteAllFavoriteUseCase
import com.example.appmarvel.domain.model.useCase.DeleteAllFavoriteUseCaseImpl
import com.example.appmarvel.domain.model.useCase.DeleteCharacterFavoriteUseCase
import com.example.appmarvel.domain.model.useCase.DeleteCharacterFavoriteUseImpl
import com.example.appmarvel.domain.model.useCase.GetCharacterUseCase
import com.example.appmarvel.domain.model.useCase.GetCharacterUseCaseImpl
import com.example.appmarvel.domain.model.useCase.GetFavoriteUseCase
import com.example.appmarvel.domain.model.useCase.GetFavoriteUseCaseImpl
import com.example.appmarvel.domain.model.useCase.SaveFavoriteUseCase
import com.example.appmarvel.domain.model.useCase.SaveFavoriteUseCaseImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val useCasesModule = module {
    factory<GetCharacterUseCase> { GetCharacterUseCaseImpl(get()) }
    factory<CharacterRepository> { CharacterRepositoryImpl(get(), get(), get(), get(),get()) }
    factory<SaveFavoriteUseCase> { SaveFavoriteUseCaseImpl(get()) }
    factory<GetFavoriteUseCase> { GetFavoriteUseCaseImpl(get()) }
    factory<DeleteCharacterFavoriteUseCase> { DeleteCharacterFavoriteUseImpl(get()) }
    factory<DeleteAllFavoriteUseCase> { DeleteAllFavoriteUseCaseImpl(get()) }
}

val domainModules = listOf<Module>( useCasesModule )
