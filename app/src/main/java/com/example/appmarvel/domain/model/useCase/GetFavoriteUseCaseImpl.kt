package com.example.appmarvel.domain.model.useCase

import com.example.appmarvel.data.remote.service.DataClass
import com.example.appmarvel.domain.model.repository.CharacterRepository

class GetFavoriteUseCaseImpl(
    private val repository: CharacterRepository
): GetFavoriteUseCase {
    override suspend fun invoke(): List<DataClass> {
        return repository.getAllFavorite()
    }
}