package com.example.appmarvel.domain.model.useCase

import com.example.appmarvel.data.remote.service.DataClass
import com.example.appmarvel.domain.model.repository.CharacterRepository

class DeleteCharacterFavoriteUseImpl(
    private val repository: CharacterRepository
): DeleteCharacterFavoriteUseCase {
    override suspend fun invoke(dataClass: DataClass) {
        repository.delete(dataClass)
    }
}