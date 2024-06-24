package com.example.appmarvel.domain.model.useCase

import com.example.appmarvel.data.remote.service.DataClass

interface SaveFavoriteUseCase {
    suspend operator fun invoke(dataClass: DataClass)
}