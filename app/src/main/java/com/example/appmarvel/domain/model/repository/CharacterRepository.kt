package com.example.appmarvel.domain.model.repository

import com.example.appmarvel.data.remote.service.DataClass
import com.example.appmarvel.domain.model.model.CharacterDomain
import com.example.appmarvel.util.base.ResourceState

interface CharacterRepository {
    suspend fun getCharacter(searchNameList: String?): ResourceState<List<CharacterDomain>>
    suspend fun saveFavorite(character: DataClass)
    suspend fun getAllFavorite(): List<DataClass>
    suspend fun delete(character: DataClass)
    suspend fun getAllDelete()
}