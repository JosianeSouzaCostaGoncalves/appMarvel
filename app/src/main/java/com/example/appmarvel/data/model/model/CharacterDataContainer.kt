package com.example.appmarvel.data.model.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CharacterDataContainer(
    @SerializedName("results")
    val results: List<CharacterModel>
) : Serializable