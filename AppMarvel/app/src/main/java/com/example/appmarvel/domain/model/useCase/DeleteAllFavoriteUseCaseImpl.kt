package com.example.appmarvel.domain.model.useCase

import com.example.appmarvel.domain.model.repository.CharacterRepository

class DeleteAllFavoriteUseCaseImpl (
    private val repository: CharacterRepository
): DeleteAllFavoriteUseCase {
    override suspend fun invoke() {
        repository.getAllDelete()
    }
}