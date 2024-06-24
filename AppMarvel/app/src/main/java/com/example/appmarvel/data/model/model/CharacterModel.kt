package com.example.appmarvel.data.model.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.appmarvel.data.model.thumbnail.Thumbnail
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "CHARACTER")
data class CharacterModel(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail
) : Serializable