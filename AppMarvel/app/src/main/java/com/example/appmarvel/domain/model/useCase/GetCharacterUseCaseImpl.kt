package com.example.appmarvel.domain.model.useCase

import com.example.appmarvel.domain.model.model.CharacterDomain
import com.example.appmarvel.domain.model.repository.CharacterRepository
import com.example.appmarvel.util.base.ResourceState

class GetCharacterUseCaseImpl(
    private val repository: CharacterRepository
) : GetCharacterUseCase {
    override suspend fun invoke(searchNameList: String?): ResourceState<List<CharacterDomain>> {
        return repository.getCharacter(searchNameList)
    }
}
