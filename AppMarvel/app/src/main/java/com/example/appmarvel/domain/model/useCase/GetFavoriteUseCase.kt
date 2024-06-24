package com.example.appmarvel.domain.model.useCase

import com.example.appmarvel.data.remote.service.DataClass

interface GetFavoriteUseCase {
    suspend operator fun invoke(): List<DataClass>
}