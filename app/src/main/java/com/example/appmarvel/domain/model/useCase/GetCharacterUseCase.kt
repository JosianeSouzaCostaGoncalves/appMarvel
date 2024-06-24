package com.example.appmarvel.domain.model.useCase

import com.example.appmarvel.domain.model.model.CharacterDomain
import com.example.appmarvel.util.base.ResourceState


interface GetCharacterUseCase {
    suspend operator fun invoke(searchNameList: String? = null) : ResourceState<List<CharacterDomain>>
}

