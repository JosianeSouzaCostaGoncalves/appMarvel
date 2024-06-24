package com.example.appmarvel.domain.model.model

import com.example.appmarvel.data.model.thumbnail.Thumbnail
import java.io.Serializable

data class CharacterDomain(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail
) : Serializable