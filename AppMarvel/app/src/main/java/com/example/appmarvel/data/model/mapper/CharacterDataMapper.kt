package com.example.appmarvel.data.model.mapper

import com.example.appmarvel.data.model.model.CharacterModel
import com.example.appmarvel.domain.model.model.CharacterDomain
import com.example.appmarvel.util.base.GenericMapper

class CharacterDataMapper : GenericMapper<CharacterModel, CharacterDomain> {

    override fun mapToDomain(type: CharacterModel): CharacterDomain {
        return CharacterDomain(
            type.id,
            type.name,
            type.description,
            type.thumbnail
        )
    }

    override fun mapFromDomain(type: CharacterDomain): CharacterModel {
        return CharacterModel(
            type.id,
            type.name,
            type.description,
            type.thumbnail
        )
    }
}