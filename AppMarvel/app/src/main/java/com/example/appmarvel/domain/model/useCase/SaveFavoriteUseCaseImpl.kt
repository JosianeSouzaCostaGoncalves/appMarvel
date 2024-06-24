package com.example.appmarvel.domain.model.useCase

import com.example.appmarvel.data.remote.service.DataClass
import com.example.appmarvel.domain.model.repository.CharacterRepository

class SaveFavoriteUseCaseImpl (
    private val repository: CharacterRepository
): SaveFavoriteUseCase {
    override suspend fun invoke(dataClass: DataClass) {
        return repository.saveFavorite(dataClass)
    }
}