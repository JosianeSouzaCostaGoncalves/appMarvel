package com.example.appmarvel.domain.model.useCase

import com.example.appmarvel.data.remote.service.DataClass

interface DeleteCharacterFavoriteUseCase {
    suspend operator fun invoke(dataClass: DataClass)
}