package com.example.appmarvel.view.model.mapper

import com.example.appmarvel.util.base.GenericMapper
import com.example.appmarvel.domain.model.model.CharacterDomain
import com.example.appmarvel.view.model.model.CharacterView


class CharacterViewMapper : GenericMapper<CharacterView, CharacterDomain> {

    override fun mapToDomain(type: CharacterView): CharacterDomain {
        return CharacterDomain(
            type.id,
            type.name,
            type.description,
            type.thumbnail,
        )
    }

    override fun mapFromDomain(type: CharacterDomain): CharacterView {
        return CharacterView(
            type.id,
            type.name,
            type.description,
            type.thumbnail,
        )
    }
}